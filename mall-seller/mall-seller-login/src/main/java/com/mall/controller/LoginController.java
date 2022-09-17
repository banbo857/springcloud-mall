package com.mall.controller;

import com.mall.pojo.Seller;
import com.mall.service.SellerLoginService;
import com.mall.utils.RedisUtil;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
@RequestMapping("/seller/login")
public class LoginController {

    @Autowired
    private SellerLoginService loginService;
    @Autowired
    private RedisUtil redisUtil;

    private final static String SESSION_KEY = "seller:session:";

    public Result defaultFallback() {
        return Result.error("hystrix->登录服务繁忙,请稍后再试");
    }


    /**
     * 登录
     */
    @HystrixCommand
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        Seller seller = loginService.login(account, password);
        if (seller == null) {
            return Result.error("账号或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("seller", seller);
        session.setMaxInactiveInterval(60 * 60 * 24 * 7);//设置session过期时间为7天
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setDomain("127.0.0.1");
        cookie.setMaxAge(60 * 60 * 24 * 7);//设置cookie过期时间为一周
        response.addCookie(cookie);
        redisUtil.set(SESSION_KEY + session.getId(), seller, 60 * 60 * 24 * 7);
        return Result.build();
    }

    @RequestMapping("/logout")
    @ResponseBody
    @HystrixCommand
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("seller");
        return Result.build();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    @ResponseBody
    @HystrixCommand
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("storeName") String storeName,
                           @RequestParam("storeIntroduce") String storeIntroduce) {
        Integer res = loginService.register(account, password, storeName, storeIntroduce);
        if (res == -1) {
            return  Result.error("账号已存在");
        }
        return res > 0 ? Result.build(): Result.error("注册失败");
    }


}
