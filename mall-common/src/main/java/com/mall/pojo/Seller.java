package com.mall.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商家
 */
@Data
@Accessors(chain = true)
public class Seller implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * seller_id
     */
    private Integer sellerId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 店铺名
     */
    private String storeName;

    /**
     * 店铺介绍
     */
    private String storeIntroduce;

    /**
     * 余额
     */
    private double balance;

    /**
     * 订单 非数据库字段
     */
    private List<GoodsOrder> goodsOrders;

    /**
     * 商品 非数据库字段
     */
    private List<Goods> goodsList;


}
