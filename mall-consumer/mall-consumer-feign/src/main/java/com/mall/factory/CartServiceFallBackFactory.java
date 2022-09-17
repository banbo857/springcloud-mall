package com.mall.factory;

import com.mall.service.FeignCartService;
import com.mall.service.FeignGoodsService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartServiceFallBackFactory implements FallbackFactory<FeignCartService> {
    @Override
    public FeignCartService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignCartService() {
            /**
             * 添加购物车
             *
             * @param goodsId
             */
            @Override
            public Result addCart(String goodsId) {
                return Result.error("feign调用->购物车添加异常");
            }

            /**
             * 获取购物车
             */
            @Override
            public Result getCart() {
                return Result.error("feign调用->购物车获取异常");
            }

            /**
             * 删除物品
             *
             * @param goodsId
             */
            @Override
            public Result deleteCart(String goodsId) {
                return Result.error("feign调用->购物车删除异常");

            }
        };
    }
}
