package com.mall.service;


import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;

import java.util.List;

public interface SellerOrderService {

    /**
     * 获取商家全部订单
     */
    List<GoodsOrder> getAllOrder();

    /**
     * 查询订单
     */
    List<GoodsOrder>  searchOrder(String keyWord);

    /**
     * 修改订单状态
     */
    int updateStatusByOrderId(String orderId, String status);

    /**
     * 更新发货时间 物流信息
     */
    int updateDeliverTimeByOrderId(String orderId, String deliverTime, Integer logisticsId);

    /**
     * 新增物流信息
     */
    int addLogistics(String company, String number);

}
