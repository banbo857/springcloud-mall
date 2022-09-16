package com.mall.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 收货地址
 */
@Data
@Accessors(chain = true)
public class ConsumerAddress implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * address_id
     */
    private Integer addressId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 消费者id
     */
    private Integer consumerId;


}
