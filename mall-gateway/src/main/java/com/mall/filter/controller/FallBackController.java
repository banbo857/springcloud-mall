package com.mall.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FallBackController {

    @GetMapping("/fallback")

    public String fallback() {
        return "服务器繁忙，请稍后再试";
    }

}
