package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.factory.GoodsServiceFallBackFactory;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall-consumer-goods", fallbackFactory = GoodsServiceFallBackFactory.class, configuration = FeignConfig.class)
@Component
public interface FeignGoodsService {

    @RequestMapping("/consumer/goods/list")
    Result goodsList();

    @RequestMapping("/consumer/goods/search")
    Result search(@RequestParam("title") String title);

    @RequestMapping("/consumer/goods/getGoodsById")
    Result getGoodsById(@RequestParam("goodsId") String goodsId);
}
