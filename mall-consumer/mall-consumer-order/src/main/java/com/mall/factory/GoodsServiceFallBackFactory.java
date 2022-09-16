package com.mall.factory;

import com.mall.service.FeignGoodsService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoodsServiceFallBackFactory implements FallbackFactory<FeignGoodsService> {
    @Override
    public FeignGoodsService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignGoodsService(){
            @Override
            public Result getGoodsById(String goodsId) {
                return Result.error("获取商品信息失败");
            }

        };
    }
}
