package com.mall.dao;

import com.mall.pojo.Evaluate;
import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;
import com.mall.pojo.Logistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerOrderDao {
    /**
     * 获取全部订单
     */
    List<GoodsOrder> getAllOrder(@Param("sellerId") Integer sellerId);


    /**
     * 获取全部订单中的商品
     */
    List<Goods> getGoodsBySellerId(@Param("sellerId") Integer sellerId);

    int updateStatusByOrderId(@Param("orderId") String orderId, @Param("status") String status);


    int updateDeliverTimeByOrderId(@Param("orderId") String orderId, @Param("deliverTime") String deliverTime, @Param("logisticsId") Integer logisticsId);

    int addLogistics(@Param("logistics") Logistics logistics);

}
