package com.vs.vision.pojo.pre;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("pre_diagnosis_result")
public class DiagnosisResult {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private Integer parentId;
	private Integer disType;
	private String symptomName;
	private Date gmtCreate;
	private Date gmtModified;
}
