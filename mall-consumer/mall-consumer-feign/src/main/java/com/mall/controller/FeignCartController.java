package com.mall.controller;

import com.mall.service.FeignCartService;
import com.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/cart")
public class FeignCartController {

    @Autowired
    private FeignCartService feignCartService;

    /**
     * 添加购物车
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result addCart(@RequestParam("goodsId") String goodsId) {
        return feignCartService.addCart(goodsId);
    }

    /**
     * 获取购物车
     */
    @RequestMapping("/get")
    @ResponseBody
    public Result getCart() {
        return feignCartService.getCart();
    }

    /**
     * 删除物品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteCart(@RequestParam("goodsId") String goodsId) {
        return feignCartService.deleteCart(goodsId);
    }


}
