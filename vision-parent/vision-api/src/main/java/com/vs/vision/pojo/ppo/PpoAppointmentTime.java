package com.vs.vision.pojo.ppo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("ppo_appintmenttime")
public class PpoAppointmentTime {
	   @TableId(type = IdType.AUTO)
		private Long id;
		private Integer appointmentId;
		private Date startTime;
		private Date endTime;
		private Date gmtCreate;
		private Date gmtModified;
}
