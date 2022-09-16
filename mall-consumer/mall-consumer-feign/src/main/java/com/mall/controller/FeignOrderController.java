package com.mall.controller;

import com.mall.pojo.Goods;
import com.mall.service.FeignGoodsService;
import com.mall.service.FeignOrderService;
import com.mall.service.OrderService;
import com.mall.service.PayService;
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
@RequestMapping("/consumer/order")
public class FeignOrderController {

    @Autowired
    private FeignOrderService feignOrderService;


    public Result defaultFallback() {
        return Result.error("系统繁忙，请稍后再试");
    }


    /**
     * 获取全部订单
     */
    @RequestMapping("/getAllOrder")
    @ResponseBody
    public Result getAllOrder() {
        return feignOrderService.getAllOrder();
    }

    /**
     * 搜索订单
     */
    @RequestMapping("/searchOrder")
    @ResponseBody
    public Result searchOrder(@RequestParam("keyWord") String keyWord) {
        return feignOrderService.searchOrder(keyWord);
    }

    /**
     * 生成订单
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public Result createOrder(@RequestParam("goodsIds") String goodsIds,
                              @RequestParam("goodsNums") String goodsNums,
                              @RequestParam("addressId") String addressId) {
        return feignOrderService.createOrder(goodsIds, goodsNums, addressId);
    }


    /**
     * 订单付款
     */
    @RequestMapping("/pay")
    @ResponseBody
    public Result pay(@RequestParam("orderId") String orderId) {
        return feignOrderService.pay(orderId);
    }


    /**
     * 取消订单
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public Result cancelOrder(@RequestParam("orderId") String orderId) {
        return feignOrderService.cancelOrder(orderId);
    }

    /**
     * 确认收货
     */
    @RequestMapping("/confirmReceipt")
    @ResponseBody
    public Result confirmReceipt(@RequestParam("orderId") String orderId) {
        return feignOrderService.confirmReceipt(orderId);
    }

    /**
     * 申请退货
     */
    @RequestMapping("/applyReturn")
    @ResponseBody
    public Result applyReturn(@RequestParam("orderId") String orderId) {
        return feignOrderService.applyReturn(orderId);
    }

    /**
     * 订单评价
     */
    @RequestMapping("/evaluate")
    @ResponseBody
    public Result evaluate(@RequestParam("goodsId") String goodsId,
                           @RequestParam("content") String content,
                           @RequestParam("evaluateLevel") String evaluateLevel) {
        return feignOrderService.evaluate(goodsId, content, evaluateLevel);
    }


}
