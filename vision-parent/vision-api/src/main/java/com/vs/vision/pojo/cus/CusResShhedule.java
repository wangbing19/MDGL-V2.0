package com.vs.vision.pojo.cus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)    //开启链式结构
@TableName("cus_schedule")
public class CusResShhedule {

	@TableId(type = IdType.AUTO)
	/**id*/
	private int id;
	/**课程表id*/
	private Integer cus_schedule_id;
	/**资源配置表id*/
	private Integer res_symptom_id;
	
}
