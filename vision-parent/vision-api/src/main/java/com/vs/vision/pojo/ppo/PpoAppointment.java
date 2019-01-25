package com.vs.vision.pojo.ppo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
@TableName("ppo_appointment")
public class PpoAppointment {
    @TableId(type = IdType.AUTO)
    /**
     * 主键ID
     */
    private Long id;
    private String trainerName;
    private String gender;
    private String tel;
    private String post;
    private String description;
    private String resume;
    private String userName;
    private Date gmtCreate;
    private Date gmtModified;

   
}
