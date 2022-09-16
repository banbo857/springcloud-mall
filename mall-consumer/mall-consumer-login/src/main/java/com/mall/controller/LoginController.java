package com.mall.controller;

import com.mall.service.LoginService;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
@RequestMapping("/consumer/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 登录
     */
    @HystrixCommand(commandProperties = {
            // 10s 内请求数大于 10 个就启动熔断器，当请求符合熔断条件触发 fallbackMethod 默认 20 个
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,
                    value = "2"),
            // 请求错误率大于 50% 就启动熔断器，然后 for 循环发起重试请求，当请求符合熔断条件触发 fallbackMethod
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,
                    value = "50"),
            // 熔断多少秒后去重试请求，默认 5s
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,
                    value = "5000"),
    })
    @PostMapping("/login")
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password) {
        log.info("consumer: " + account + " login " + " password: " + password);
        return loginService.login(account, password) > 0 ? Result.build(): Result.error("登录失败");
    }

    /**
     * 注册
     */
    @PostMapping("/register")
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
        return Result.error("服务器繁忙，请稍后再试");
    }


}
