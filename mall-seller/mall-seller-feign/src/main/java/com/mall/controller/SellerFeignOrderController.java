package com.mall.controller;

import com.mall.service.SellerFeignOrderService;
import com.mall.utils.DateUtils;
import com.mall.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/seller/order")
public class SellerFeignOrderController {

    @Autowired
    private SellerFeignOrderService sellerFeignOrderService;


    public Result defaultFallback() {
        return Result.error("系统繁忙，请稍后再试");
    }


    /**
     * 获取全部订单
     */
    @RequestMapping("/getAllOrder")
    @ResponseBody
    public Result getAllOrder() {
        return sellerFeignOrderService.getAllOrder();
    }

    /**
     * 搜索订单
     */
    @RequestMapping("/searchOrder")
    @ResponseBody
    public Result searchOrder(@RequestParam("keyWord") String keyWord) {
        return sellerFeignOrderService.searchOrder(keyWord);
    }

    /**
     * 修改订单状态
     */
    @RequestMapping("/updateOrderStatus")
    @ResponseBody
    public Result updateOrderStatus(@RequestParam("orderId") String orderId,
                                    @RequestParam("status") String status) {
        return sellerFeignOrderService.updateOrderStatus(orderId, status);
    }

    /**
     * 发货
     */
    @RequestMapping("/sendGoods")
    @ResponseBody
    public Result sendGoods(@RequestParam("orderId") String orderId,
                            @RequestParam("company") String company,
                            @RequestParam("number") String number) {
        return sellerFeignOrderService.sendGoods(orderId, company, number);
    }

}
