package com.mall.factory;

import com.mall.service.SellerFeignOrderService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceFallBackFactory implements FallbackFactory<SellerFeignOrderService> {
    @Override
    public SellerFeignOrderService create(Throwable throwable) {
        return new SellerFeignOrderService() {
            @Override
            public Result getAllOrder() {
                return Result.error("订单服务异常");
            }

            @Override
            public Result searchOrder(String keyWord) {
                return  Result.error("订单服务异常");
            }

            @Override
            public Result updateOrderStatus(String orderId, String status) {
                return  Result.error("订单服务异常");
            }

            @Override
            public Result sendGoods(String orderId, String company, String number) {
                return  Result.error("订单服务异常");
            }
        };
    }
}
