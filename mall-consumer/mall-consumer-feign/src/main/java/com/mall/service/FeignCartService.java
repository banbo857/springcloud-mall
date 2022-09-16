package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall-consumer-cart", configuration = FeignConfig.class)
@Component
public interface FeignCartService {
    /**
     * 添加购物车
     */
    @RequestMapping("/consumer/cart/add")
    Result addCart(@RequestParam("goodsId") String goodsId);

    /**
     * 获取购物车
     */
    @RequestMapping("/consumer/cart/get")
    Result getCart();

    /**
     * 删除物品
     */
    @RequestMapping("/consumer/cart/delete")
    Result deleteCart(@RequestParam("goodsId") String goodsId);
}
