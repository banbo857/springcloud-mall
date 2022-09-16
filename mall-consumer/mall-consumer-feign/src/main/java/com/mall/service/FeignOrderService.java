package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "mall-consumer-order", configuration = FeignConfig.class)
public interface FeignOrderService {


    /**
     * 获取全部订单
     */
    @RequestMapping("/consumer/order/getAllOrder")
    Result getAllOrder();

    /**
     * 搜索订单
     */
    @RequestMapping("/consumer/order/searchOrder")
    Result searchOrder(@RequestParam("keyWord") String keyWord);

    /**
     * 生成订单
     */
    @RequestMapping("/consumer/order/createOrder")
    Result createOrder(@RequestParam("goodsIds") String goodsIds,
                       @RequestParam("goodsNums") String goodsNums,
                       @RequestParam("addressId") String addressId);


    /**
     * 订单付款
     */
    @RequestMapping("/consumer/pay")
    Result pay(@RequestParam("orderId") String orderId);


    /**
     * 取消订单
     */
    @RequestMapping("/consumer/cancelOrder")
    Result cancelOrder(@RequestParam("orderId") String orderId);

    /**
     * 确认收货
     */
    @RequestMapping("/consumer/confirmReceipt")
    Result confirmReceipt(@RequestParam("orderId") String orderId);

    /**
     * 申请退货
     */
    @RequestMapping("/consumer/applyReturn")
    Result applyReturn(@RequestParam("orderId") String orderId);

    /**
     * 评价
     */
    @RequestMapping("/consumer/evaluate")
    Result evaluate(@RequestParam("goodsId") String goodsId,
                    @RequestParam("content") String content,
                    @RequestParam("evaluateLevel") String evaluateLevel);
}
