package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.factory.GoodsServiceFallBackFactory;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "mall-seller-goods", fallbackFactory = GoodsServiceFallBackFactory.class, configuration = FeignConfig.class)
@Component
public interface SellerFeignGoodsService {

    /**
     * 获取商品列表
     */
    @RequestMapping("/seller/goods/list")
    Result goodsList();

    /**
     * 查询商品
     */
    @RequestMapping("/seller/goods/search")
    Result search(@RequestParam("title") String title);

    /**
     * 根据id查询商品
     */
    @RequestMapping("/seller/goods/getGoodsById")
    Result getGoodsById(@RequestParam("goodsId") String goodsId);

    /**
     * 添加商品
     */
    @RequestMapping("/seller/goods/addGoods")
    Result addGoods(@RequestParam("title") String title,
                    @RequestParam("introduce") String introduce,
                    @RequestParam("price") String price,
                    @RequestParam("stock") String stock,
                    @RequestParam("img") String img,
                    @RequestParam("type") String type,
                    @RequestParam("file") MultipartFile file);

    /**
     * 删除商品
     */
    @RequestMapping("/seller/goods/deleteGoods")
    Result deleteGoods(@RequestParam("goodsId") String goodsId);

    /**
     * 修改商品
     */
    @RequestMapping("/seller/goods/updateGoods")
    Result updateGoods(@RequestParam("goodsId") String goodsId,
                       @RequestParam("title") String title,
                       @RequestParam("introduce") String introduce,
                       @RequestParam("price") String price,
                       @RequestParam("stock") String stock,
                       @RequestParam("img") String img,
                       @RequestParam("type") String type);

    /**
     * 上架商品\下架商品
     */
    @RequestMapping("/seller/goods/upStatus")
    Result upStatus(@RequestParam("goodsId") String goodsId,
                    @RequestParam("status") String status);


    /**
     * 获取图片
     */
    @PostMapping("/seller/goods/getImg")
    Result getImg(@RequestParam("path") String path);
}
