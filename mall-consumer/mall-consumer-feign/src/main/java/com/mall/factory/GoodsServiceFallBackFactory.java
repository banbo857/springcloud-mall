package com.mall.factory;

import com.mall.service.FeignGoodsService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GoodsServiceFallBackFactory implements FallbackFactory<FeignGoodsService> {
    @Override
    public FeignGoodsService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignGoodsService() {
            @Override
            public Result goodsList() {
                return Result.error("服务器异常，请稍后再试");
            }

            @Override
            public Result search(String title) {
                return Result.error("服务器异常，请稍后再试");
            }

            @Override
            public Result getGoodsById(String goodsId) {
                return Result.error("服务器异常，请稍后再试");
            }
        };
    }
}
