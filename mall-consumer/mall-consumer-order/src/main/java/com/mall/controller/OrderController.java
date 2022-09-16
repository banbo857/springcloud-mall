package com.mall.controller;

import com.mall.pojo.Goods;
import com.mall.service.FeignGoodsService;
import com.mall.service.FeignPayService;
import com.mall.service.OrderService;
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
@RequestMapping("/consumer/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private FeignPayService feignPayService;



    public Result defaultFallback() {
        return Result.error("系统繁忙，请稍后再试");
    }


    /**
     * 获取全部订单
     */
    @RequestMapping("/getAllOrder")
    @ResponseBody
    @HystrixCommand
    public Result getAllOrder() {
        return Result.data("orderList", orderService.getAllOrder(1));
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
     * 生成订单
     */
    @RequestMapping("/createOrder")
    @ResponseBody
//    @HystrixCommand
    public Result createOrder(@RequestParam("goodsIds") String goodsIds,
                              @RequestParam("goodsNums") String goodsNums,
                              @RequestParam("addressId") String addressId) {
        int res = orderService.createOrder(goodsIds, goodsNums, addressId);
        if (res > 0) {
            return Result.build();
        } else if (res == -1){
            return Result.error("商品不存在");
        }else if (res == -2){
            return Result.error("商品库存不足");
        }else if (res == -3){
            return Result.error("商品数量不能为空");
        }
        return Result.error("生成订单失败");
    }


    /**
     * 订单付款
     */
    @RequestMapping("/pay")
    @ResponseBody
    @HystrixCommand
    public Result pay(@RequestParam("orderId") String orderId) {
        if (feignPayService.pay()) {
            if (orderService.updateStatusByOrderId(orderId, 2) > 0) {
                orderService.updatePayTimeByOrderId(orderId);
                return Result.build();
            }
            return Result.error("订单状态修改失败");
        } else {
            return Result.error("支付失败");
        }
    }


    /**
     * 取消订单
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    @HystrixCommand
    public Result cancelOrder(@RequestParam("orderId") String orderId) {
        if (orderService.updateStatusByOrderId(orderId, 0) > 0) {
            return Result.build();
        }
        return Result.error("取消订单失败");
    }

    /**
     * 确认收货
     */
    @RequestMapping("/confirmReceipt")
    @ResponseBody
    @HystrixCommand
    public Result confirmReceipt(@RequestParam("orderId") String orderId) {
        if (orderService.updateStatusByOrderId(orderId, 4) > 0) {
            return Result.build();
        }
        return Result.error("确认收货失败");
    }

    /**
     * 申请退货
     */
    @RequestMapping("/applyReturn")
    @ResponseBody
    @HystrixCommand
    public Result applyReturn(@RequestParam("orderId") String orderId) {
        if (orderService.updateStatusByOrderId(orderId, 6) > 0) {
            return Result.build();
        }
        return Result.error("申请退货失败");
    }



    /**
     * 订单评价
     */
    @RequestMapping("/evaluate")
    @ResponseBody
    @HystrixCommand
    public Result evaluate(@RequestParam("goodsId") String goodsId,
                           @RequestParam("content") String content,
                           @RequestParam("evaluateLevel") String evaluateLevel) {
        if (orderService.evaluate(goodsId, content, evaluateLevel) > 0) {
            return Result.build();
        }
        return Result.error("评价失败");
    }

    //TODO 催发货


}
