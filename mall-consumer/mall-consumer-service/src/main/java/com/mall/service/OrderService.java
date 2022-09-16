package com.mall.service;


import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;

import java.util.List;

public interface OrderService {
    
    List<GoodsOrder> getAllOrder(Integer consumerId);

    List<GoodsOrder>  searchOrder(String keyWord);

    int updateStatusByOrderId(String orderId, int status);

    int updatePayTimeByOrderId(String orderId);

    int createOrder(String goodsIds, String goodsNum, String addressId);

    int evaluate(String goodsId, String content, String evaluateLevel);
}
