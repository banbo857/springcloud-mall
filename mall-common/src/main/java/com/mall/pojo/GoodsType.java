package com.mall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品分类
 */
@Data
@Accessors(chain = true)
public class GoodsType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * type_id
     */
    private Integer typeId;

    /**
     * 类型
     */
    private String type;


}
