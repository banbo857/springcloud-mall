package com.mall.factory;

import com.mall.service.FeignCartService;
import com.mall.service.FeignGoodsService;
import com.mall.service.FeignOrderService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceFallBackFactory implements FallbackFactory<FeignOrderService> {
    @Override
    public FeignOrderService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignOrderService() {
            @Override
            public Result getAllOrder() {
                return Result.error("feign调用->订单模块异常");
            }

            @Override
            public Result searchOrder(String keyWord) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result createOrder(String goodsIds, String goodsNums, String addressId) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result pay(String orderId) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result cancelOrder(String orderId) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result confirmReceipt(String orderId) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result applyReturn(String orderId) {
                return Result.error("feign调用->订单模块异常");

            }

            @Override
            public Result evaluate(String goodsId, String content, String evaluateLevel) {
                return Result.error("feign调用->订单模块异常");

            }
        };
    }
}
