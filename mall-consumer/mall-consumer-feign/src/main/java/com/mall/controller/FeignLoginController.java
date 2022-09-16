package com.mall.controller;

import com.mall.service.FeignLoginService;
import com.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/login")
public class FeignLoginController {

    @Autowired
    private FeignLoginService feignLoginService;

    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password) {
        return feignLoginService.login(account, password);
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String password) {
        return feignLoginService.register(account, password);
    }
}
