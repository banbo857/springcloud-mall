package com.mall.controller;

import com.mall.service.SellerOrderService;
import com.mall.utils.DateUtils;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
@RequestMapping("/seller/order")
public class OrderController {

    @Autowired
    private SellerOrderService orderService;

    public Result defaultFallback() {
        return Result.error("hystrix->订单服务繁忙,请稍后再试");
    }


    /**
     * 获取全部订单
     */
    @RequestMapping("/getAllOrder")
    @ResponseBody
    @HystrixCommand
    public Result getAllOrder() {
        return Result.data("orderList", orderService.getAllOrder());
    }

    /**
     * 搜索订单
     */
    @RequestMapping("/searchOrder")
    @ResponseBody
    @HystrixCommand
    public Result searchOrder(@RequestParam("keyWord") String keyWord) {
        return Result.data("orderList", orderService.searchOrder(keyWord));
    }

    /**
     * 修改订单状态
     */
    @RequestMapping("/updateOrderStatus")
    @ResponseBody
    @HystrixCommand
    public Result updateOrderStatus(@RequestParam("orderId") String orderId,
                                    @RequestParam("status") String status) {
        if (orderService.updateStatusByOrderId(orderId, status) > 0) {
            return Result.build();
        }
        return Result.error("修改失败");
    }

    /**
     * 发货
     */
    @RequestMapping("/sendGoods")
    @ResponseBody
    @HystrixCommand
    public Result sendGoods(@RequestParam("orderId") String orderId,
                            @RequestParam("company") String company,
                            @RequestParam("number") String number) {
        int logisticsId = orderService.addLogistics(company, number);
        if ( logisticsId > 0) {
            if (orderService.updateDeliverTimeByOrderId(orderId, DateUtils.fromDateH(), logisticsId) > 0) {
                return Result.build();
            }
        }
        return Result.error("发货失败");
    }




}
