package com.vs.vision.pojo.exp;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
	@TableId(type=IdType.AUTO)
    private Integer id;
    /**远程诊断表序号*/
    private Integer remoteDiagnoseId;
    /**专家回复*/
    private String expertReply;
    /**专家备注*/
    private String expertRemark;
    /**创建时间*/
    private Date gmtCreate;
    /**修改时间*/
    private Date gmtModified;
}
