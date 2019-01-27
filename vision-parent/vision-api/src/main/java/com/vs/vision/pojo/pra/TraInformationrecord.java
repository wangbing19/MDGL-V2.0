package com.vs.vision.pojo.pra;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("tra_informationrecord")
public class TraInformationrecord {
	/** 训练管理表id*/
	@TableId(type=IdType.AUTO)
	private Long id;
	/**门店id*/
	private Integer userId;
	/**上级门店id*/
	private Integer userParentId;
	/** 客户姓名*/
	private String name;
	/**客户电话*/
	private String tel;
	/**左眼视力*/
	private Double lVision;
	/**右眼视力*/
	private Double rVision;
	/**训练评分*/
	private Integer grade;
	/**训练评价*/
	private String evaluate;
	/**训练内容*/
	private String content;
	/**导师*/
	private String tutor;
	/**训练结束时间*/
	private Date endTime;
	/**表创建时间*/
	private Date gmtCreate;
	/**表修改时间*/
	private Date gmtModified;
	/**客户id*/
	private Long customerId;
	/**课程表id*/
	private Integer scheduleId;
}
