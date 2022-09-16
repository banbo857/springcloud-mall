package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.factory.OrderServiceFallBackFactory;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@FeignClient(value = "mall-seller-order", configuration = FeignConfig.class, fallbackFactory = OrderServiceFallBackFactory.class)
public interface SellerFeignOrderService {

    /**
     * 获取全部订单
     */
    @RequestMapping("/seller/order/getAllOrder")
    Result getAllOrder();

    /**
     * 搜索订单
     */
    @RequestMapping("/seller/order/searchOrder")
    Result searchOrder(@RequestParam("keyWord") String keyWord);

    /**
     * 修改订单状态
     */
    @RequestMapping("/seller/order/updateOrderStatus")
    Result updateOrderStatus(@RequestParam("orderId") String orderId,
                             @RequestParam("status") String status);

    /**
     * 发货
     */
    @RequestMapping("/seller/order/sendGoods")
    Result sendGoods(@RequestParam("orderId") String orderId,
                     @RequestParam("company") String company,
                     @RequestParam("number") String number);



}
