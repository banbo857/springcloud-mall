package com.mall.controller;

import com.mall.service.FeignGoodsService;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/goods")
public class FeignGoodsController {

    @Autowired
    private FeignGoodsService feignGoodsService;

    /**
     * 获取商品列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list() {
        return Result.data("goodsList", feignGoodsService.goodsList());
    }

    /**
     * 查询商品
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result search(@RequestParam("title") String title) {
        return Result.data("goodsList", feignGoodsService.search(title));
    }

    /**
     * 根据id查询商品
     */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    @HystrixCommand
    public Result getGoodsById(@RequestParam("goodsId") String goodsId) {
        return Result.data("goods", feignGoodsService.getGoodsById(goodsId));
    }






}
