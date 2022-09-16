package com.mall.controller;

import com.mall.service.SellerFeignGoodsService;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/seller/goods")
public class SellerFeignGoodsController {

    @Autowired
    private SellerFeignGoodsService sellerFeignGoodsService;


    /**
     * 获取商品列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list() {
        return sellerFeignGoodsService.goodsList();
    }

    /**
     * 查询商品
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result search(@RequestParam("title") String title) {
        return sellerFeignGoodsService.search(title);
    }

    /**
     * 根据id查询商品
     */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    public Result getGoodsById(@RequestParam("goodsId") String goodsId) {
        return sellerFeignGoodsService.getGoodsById(goodsId);
    }

    /**
     * 添加商品
     */
    @RequestMapping("/addGoods")
    @ResponseBody
    public Result addGoods(@RequestParam("title") String title,
                           @RequestParam("introduce") String introduce,
                           @RequestParam("price") String price,
                           @RequestParam("stock") String stock,
                           @RequestParam("img") String img,
                           @RequestParam("type") String type,
                           @RequestParam("file")MultipartFile file) {
        return sellerFeignGoodsService.addGoods(title, introduce, price, stock, img, type,file);
    }

    /**
     * 修改商品
     */
    @RequestMapping("/updateGoods")
    @ResponseBody
    public Result updateGoods(@RequestParam("goodsId") String goodsId,
                              @RequestParam("title") String title,
                              @RequestParam("introduce") String introduce,
                              @RequestParam("price") String price,
                              @RequestParam("stock") String stock,
                              @RequestParam("img") String img,
                              @RequestParam("type") String type) {
        return sellerFeignGoodsService.updateGoods(goodsId, title, introduce, price, stock, img, type);
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteGoods")
    @ResponseBody
    public Result deleteGoods(@RequestParam("goodsId") String goodsId) {
        return sellerFeignGoodsService.deleteGoods(goodsId);
    }

    /**
     * 上架商品\下架商品
     */
    @RequestMapping("/upStatus")
    @ResponseBody
    public Result upStatus(@RequestParam("goodsId") String goodsId,
                           @RequestParam("status") String status) {
        return sellerFeignGoodsService.upStatus(goodsId, status);
    }

    /**
     * 获取图片
     */
    @PostMapping("/getImg")
    @ResponseBody
    public Result getImg(@RequestParam("path") String path) {
        return sellerFeignGoodsService.getImg(path);
    }


}
