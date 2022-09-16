package com.mall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单中商品
 */
@Data
@Accessors(chain = true)
public class OrderItem implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer itemId;
    private Integer goodsId;
    private Integer orderId;
    private Integer num;
}
