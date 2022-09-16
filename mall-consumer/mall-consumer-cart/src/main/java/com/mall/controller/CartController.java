package com.mall.controller;

import com.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result addCart(@RequestParam("goodsId") String goodsId) {
        if (cartService.addCart(goodsId)) {
            return Result.build();
        }else {
            return Result.error("添加购物车失败");
        }
    }

    /**
     * 获取购物车
     */
    @RequestMapping("/get")
    @ResponseBody
    public Result getCart() {
        return Result.data("cart",cartService.getCart());
    }

    /**
     * 删除物品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteCart(@RequestParam("goodsId") String goodsId) {
        if (cartService.deleteCart(goodsId)) {
            return Result.build();
        }else {
            return Result.error("删除物品失败");
        }
    }


}
