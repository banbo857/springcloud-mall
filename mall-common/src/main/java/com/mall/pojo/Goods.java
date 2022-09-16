package com.mall.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 商品
 */
@Data
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * goods_id
     */
    private Integer goodsId;

    /**
     * 标题
     */
    private String title;

    /**
     * 价格
     */
    private double price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存
     */
    private String images;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 状态 0下架 1上架
     */
    private Integer status;

    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 商家id
     */
    private Integer sellerId;

    /**
     * 评价 非数据库字段
     */
    private List<Evaluate> evaluateList;


}
