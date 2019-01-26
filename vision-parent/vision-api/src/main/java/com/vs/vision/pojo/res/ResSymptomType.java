package com.vs.vision.pojo.res;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vs.vision.pojo.ppo.PpoAppointment;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("res_symptom_type")
public class ResSymptomType {
	/**症状类型实体类,封装实体数据*/
	private Integer id;
	private Integer userId;
	/**服务名称*/
	private String serviceName;
	/**选择状态*/
	private Integer chooseType;
	/**是否生效*/
	private Integer serviceState;
	/**表创建时间*/
	private Date gmtCreate;
	/**表修改时间*/
	private Date gmtModified;

		
}
