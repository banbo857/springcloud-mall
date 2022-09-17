package com.mall.service;


import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;

import java.util.List;

public interface OrderService {

    List<GoodsOrder> getAllOrder(String sessionId);

    List<GoodsOrder> searchOrder(String keyWord, String sessionId);

    int updateStatusByOrderId(String orderId, int status);

    int updatePayTimeByOrderId(String orderId);

    int updateDeliverTimeByOrderId(String orderId);

    int createOrder(String goodsIds, String goodsNum, String addressId, String sessionId);

    int evaluate(String goodsId, String content, String evaluateLevel, String sessionId);
}
