package com.mall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 订单
 */
@Data
@Accessors(chain = true)
public class GoodsOrder implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer orderId;

    /**
     * 订单号
     */
    private String number;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 付款时间
     */
    private String payTime;

    /**
     * 发货时间
     */
    private String deliverTime;

    /**
     * 成交时间
     */
    private String dealTime;

    /**
     * 价格
     */
    private double price;

    /**
     * 状态 0 -已取消 1-新创建待付款 2-已付款待发货 3-已发货待收货 4-确认收货待评价 5-已完成 6-退款中 7-已退款
     */
    private Integer status;

    /**
     * 商家id
     */
    private Integer sellerId;

    /**
     * 物流id
     */
    private Integer logisticsId;

    /**
     * 消费者id
     */
    private Integer consumerId;

    /**
     * 收货地址id
     */
    private Integer addressId;

    /**
     * 商品
     */
    private List<OrderItem> itemList;


}
