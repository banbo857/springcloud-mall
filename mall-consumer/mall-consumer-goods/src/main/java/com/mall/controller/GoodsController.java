package com.mall.controller;

import com.mall.service.GoodsService;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/goods")
@DefaultProperties(defaultFallback = "defaultFallback")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    public Result defaultFallback() {
        return Result.error("请求失败，请稍后再试--hystrix");
    }

    /**
     * 获取商品列表
     */
    @RequestMapping("/list")
    @HystrixCommand
    @ResponseBody
    public Result list() {
        return Result.data("goodsList",goodsService.goodsList());
    }

    /**
     * 查询商品
     */
    @RequestMapping("/search")
    @ResponseBody
    @HystrixCommand
    public Result search(@RequestParam("title") String title) {
        return Result.data("goodsList",goodsService.search(title));
    }

    /**
     * 根据id查询商品
     */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    @HystrixCommand
    public Result getGoodsById(@RequestParam("goodsId") String goodsId) {
        return Result.data("goods",goodsService.getGoodsById(goodsId));
    }






}
