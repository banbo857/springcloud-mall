package com.mall.service;

import com.mall.factory.PayServiceFallBackFactory;
import com.mall.pojo.Goods;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall-consumer-goods", fallbackFactory = PayServiceFallBackFactory.class)
@Component
public interface FeignGoodsService {

    @RequestMapping("/consumer/goods/getGoodsById")
    Result getGoodsById(@RequestParam("goodsId") String goodsId);
}
