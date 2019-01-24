package com.vs.vision.pojo.exp;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对应专家表
 * @author tarena
 *
 */
@Data
@Accessors(chain=true)
@TableName("exp_expert")
public class Expert {
    /**专家表序号*/
    private Integer id;
    /**专家姓名*/
    private String expertName;
    /**专家电话*/
    private String expertTel;
    /**专家信息*/
    private String expertMessage;
    /**预约时间*/
    private String appointmentTime;
}
