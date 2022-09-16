package com.mall.controller;

import com.mall.service.SellerGoodsService;
import com.mall.utils.FastdfsUtils;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/seller/goods")
@DefaultProperties(defaultFallback = "defaultFallback")
//@CrossOrigin
public class GoodsController {

    @Autowired
    private SellerGoodsService goodsService;


    public Result defaultFallback() {
        return Result.error("hystrix->商品服务繁忙,请稍后再试");
    }

    /**
     * 获取商品列表
     */
    @RequestMapping("/list")
    @HystrixCommand
    @ResponseBody
    public Result list(HttpServletRequest request) {
        return Result.data("goodsList", goodsService.goodsList(request));
    }

    /**
     * 查询商品
     */
    @RequestMapping("/search")
    @ResponseBody
    @HystrixCommand
    public Result search(@RequestParam("title") String title) {
        return Result.data("goodsList", goodsService.search(title));
    }

    /**
     * 根据id查询商品
     */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    @HystrixCommand
    public Result getGoodsById(@RequestParam("goodsId") String goodsId) {
        return Result.data("goods", goodsService.getGoodsById(goodsId));
    }

    /**
     * 添加商品
     */
    @RequestMapping("/addGoods")
    @ResponseBody
    @HystrixCommand
    public Result addGoods(@RequestParam("title") String title,
                           @RequestParam("introduce") String introduce,
                           @RequestParam("price") String price,
                           @RequestParam("stock") String stock,
                           @RequestParam("img") String img,
                           @RequestParam("type") String type,
                           @RequestParam("file") MultipartFile file) {
        if (goodsService.addGoods(title, introduce, price, stock, img, type,file) > 0) {
            return Result.build();
        }
        return Result.error("添加失败");
    }

    /**
     * 修改商品
     */
    @RequestMapping("/updateGoods")
    @ResponseBody
    @HystrixCommand
    public Result updateGoods(@RequestParam("goodsId") String goodsId,
                              @RequestParam("title") String title,
                              @RequestParam("introduce") String introduce,
                              @RequestParam("price") String price,
                              @RequestParam("stock") String stock,
                              @RequestParam("img") String img,
                              @RequestParam("type") String type) {
        if (goodsService.updateGoods(goodsId, title, introduce, price, stock, img, type) > 0) {
            return Result.build();
        }
        return Result.error("修改失败");
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteGoods")
    @ResponseBody
    @HystrixCommand
    public Result deleteGoods(@RequestParam("goodsId") String goodsId) {
        if (goodsService.deleteGoods(goodsId) > 0) {
            return Result.build();
        }
        return Result.error("删除失败");
    }

    /**
     * 上架商品\下架商品
     */
    @RequestMapping("/upStatus")
    @ResponseBody
    @HystrixCommand
    public Result upStatus(@RequestParam("goodsId") String goodsId,
                           @RequestParam("status") Integer status) {
        if (goodsService.upStatus(goodsId, status) > 0) {
            return Result.build();
        }
        return Result.error("操作失败");
    }


    /**
     * 获取图片
     */
    @PostMapping("/getImg")
    @ResponseBody
    @HystrixCommand
    public Result getImg(@RequestParam("path") String path) {
        byte[] res = goodsService.getImg(path);
        if (res == null) {
            return Result.error("获取图片失败");
        }else {
            return Result.data("img",res);
        }
    }


}
