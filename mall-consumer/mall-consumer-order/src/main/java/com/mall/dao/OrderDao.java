package com.mall.dao;

import com.mall.pojo.Evaluate;
import com.mall.pojo.Goods;
import com.mall.pojo.GoodsOrder;
import com.mall.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    /**
     * 获取全部订单
     */
    List<GoodsOrder> getAllOrder(@Param("consumerId") Integer consumerId);


    /**
     * 获取全部订单中的商品
     */
    List<Goods> getGoodsByConsumerId(@Param("consumerId") Integer consumerId);

    int updateStatusByOrderId(@Param("orderId") String orderId, @Param("status") int status);

    int updatePayTimeByOrderId(@Param("orderId") String orderId, @Param("payTime") String payTime);

    int createOrder(@Param("goodsOrder") GoodsOrder goodsOrder);

    int evaluate(@Param("evaluate") Evaluate evaluate);

    int addItem(@Param("orderItems") List<OrderItem> orderItems);
}
