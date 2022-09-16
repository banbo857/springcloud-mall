//package com.mall.controller;
//
//import com.mall.service.SellerFeignLoginService;
//import com.mall.utils.Result;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/seller/login")
//public class SellerFeignLoginController {
//
//    @Autowired
//    private SellerFeignLoginService sellerFeignLoginService;
//
//
//    /**
//     * 登录
//     */
//    @PostMapping("/login")
//    @ResponseBody
//    public Result login(@RequestParam("account") String account,
//                        @RequestParam("password") String password) {
//        return sellerFeignLoginService.login(account, password);
//    }
//
//    /**
//     * 注册
//     */
//    @PostMapping("/register")
//    @ResponseBody
//    public Result register(@RequestParam("account") String account,
//                           @RequestParam("password") String password,
//                           @RequestParam("storeName") String storeName,
//                           @RequestParam("storeIntroduce") String storeIntroduce) {
//        return sellerFeignLoginService.register(account, password, storeName, storeIntroduce);
//    }
//}
