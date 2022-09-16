package com.mall.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消费者
 */
@Data
@Accessors(chain = true)
public class Consumer implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * consumer_id
     */
    private Integer consumerId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 订单
     */
    private List<GoodsOrder> goodsOrderList;

    /**
     * 我的评价 非数据库字段
     */
    private List<Evaluate> evaluateList;

    /**
     * 收货地址 非数据库字段
     */
    private List<ConsumerAddress> addressList;

    /**
     * 购物车 非数据库字段
     */
    private List<Goods> cartList;



}
