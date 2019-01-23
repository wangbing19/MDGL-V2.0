 package com.vs.pre.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("pre_diagnosis_user")
public class DiagnosisUser {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private Integer userId;
	private Integer diagnosisId;
	private Date gmtCreate;
	private Date gmtModified;
}
