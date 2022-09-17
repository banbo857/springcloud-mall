package com.mall.controller;

import com.mall.pojo.Consumer;
import com.mall.pojo.Seller;
import com.mall.service.LoginService;
import com.mall.utils.RedisUtil;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
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
@RequestMapping("/consumer/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtil redisUtil;

    private final static String SESSION_KEY = "consumer:session:";

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
        Consumer consumer = loginService.login(account, password);
        if (consumer == null) {
            return Result.error("账号或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("consumer", consumer);
        session.setMaxInactiveInterval(60 * 60 * 24 * 7);//设置session过期时间为7天
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setDomain("127.0.0.1");
        cookie.setMaxAge(60 * 60 * 24 * 7);//设置cookie过期时间为一周
        response.addCookie(cookie);
        redisUtil.set(SESSION_KEY + session.getId(), consumer, 60 * 60 * 24 * 7);
        return Result.build();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    @HystrixCommand
    @ResponseBody
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                            @RequestParam("nickName") String nickName,
                            @RequestParam("phone") String phone) {
        Integer res = loginService.register(account, password, nickName, phone);
        if (res == -1) {
            return  Result.error("账号已存在");
        }
        return res > 0 ? Result.build(): Result.error("注册失败");
    }

    public Result defaultFallback() {
        return Result.error("hystrix->登录服务异常");
    }


}
