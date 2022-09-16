package com.mall.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 评价回复
 */
@Data
@Accessors(chain = true)
public class EvaluateReply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * reply_id
     */
    private Integer replyId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 评价id
     */
    private Integer evaluateId;


}
