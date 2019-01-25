package com.vs.vision.pojo.ppo.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
@Data
public class PpoAppTime {
		private Integer appointmentId;
		private  String startTime;
		private String endTime;
}
