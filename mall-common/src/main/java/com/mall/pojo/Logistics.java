package com.mall.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 物流信息
 */
@Data
@Accessors(chain = true)
public class Logistics implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * logistics_id
     */
    private Integer logisticsId;

    /**
     * 物流公司
     */
    private String company;

    /**
     * 物流单号
     */
    private String number;

    /**
     * 物流进度 非数据库字段
     */
    private String progress;


}
