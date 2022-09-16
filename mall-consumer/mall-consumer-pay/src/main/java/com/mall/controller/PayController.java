package com.mall.controller;

import com.mall.service.PayService;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
@RequestMapping("/consumer/pay")
public class PayController {

    @Autowired
    private PayService payService;

    public Result defaultFallback() {
        return Result.error("系统繁忙，请稍后再试");
    }

    @RequestMapping("/pay")
    @ResponseBody
    @HystrixCommand
    public Result pay() {
        return payService.pay()?Result.build():Result.error("支付失败");
    }

}
