package com.vs.pre.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("pre_diagnosis_desc")
public class DiagnosisDesc {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private Integer diagnosisId;
	private String diagnosisDesc;
	private Date gmtCreate;
	private Date gmtModified;
}
