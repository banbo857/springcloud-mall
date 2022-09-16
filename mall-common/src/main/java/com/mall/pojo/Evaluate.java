package com.mall.pojo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 评价
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Evaluate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * evaluate_id
     */
    private Integer evaluateId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 等级
     */
    private Integer evaluateLevel;

    /**
     * 内容
     */
    private String content;

    /**
     * 消费者id
     */
    private Integer consumerId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 回复id
     */
    private Integer replyId;


}
