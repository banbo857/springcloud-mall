package com.mall.controller;

import com.mall.service.CartService;
import com.mall.utils.Result;
import com.mall.utils.SessionUtils;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
@DefaultProperties(defaultFallback = "defaultFallback")
public class CartController {

    @Autowired
    private CartService cartService;

    public Result defaultFallback() {
        return Result.error("hystrix->购物车服务异常");
    }

    /**
     * 添加购物车
     */
    @RequestMapping("/add")
    @ResponseBody
    @HystrixCommand
    public Result addCart(@RequestParam("goodsId") String goodsId, HttpServletRequest request) {
        String JSessionId = SessionUtils.getSessionId(request);
        if (cartService.addCart(goodsId, JSessionId)) {
            return Result.build();
        } else {
            return Result.error("添加购物车失败");
        }
    }

    /**
     * 获取购物车
     */
    @RequestMapping("/get")
    @ResponseBody
    @HystrixCommand
    public Result getCart(HttpServletRequest request) {
        String JSessionId = SessionUtils.getSessionId(request);
        return Result.data("cart", cartService.getCart(JSessionId));
    }

    /**
     * 删除物品
     */
    @RequestMapping("/delete")
    @ResponseBody
    @HystrixCommand
    public Result deleteCart(@RequestParam("goodsId") String goodsId, HttpServletRequest request) {
        String JSessionId = SessionUtils.getSessionId(request);
        if (cartService.deleteCart(goodsId, JSessionId)) {
            return Result.build();
        } else {
            return Result.error("删除物品失败");
        }
    }


}
