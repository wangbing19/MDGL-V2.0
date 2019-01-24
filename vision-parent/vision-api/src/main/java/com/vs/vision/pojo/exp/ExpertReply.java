package com.vs.vision.pojo.exp;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对应专家回复表
 */
@Data
@Accessors(chain=true)
@TableName("exp_expert_reply")
public class ExpertReply {
    /**专家回复表序号*/
    private Integer id;
    /**远程诊断表序号*/
    private Integer remoteDiagnoseId;
    /**专家回复*/
    private String expertReply;
    /**专家备注*/
    private String expertRemark;
}
