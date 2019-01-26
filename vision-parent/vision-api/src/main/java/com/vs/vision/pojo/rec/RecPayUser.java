package com.vs.vision.pojo.rec;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("rec_pay_user")
public class RecPayUser {
	@TableId(type=IdType.AUTO)
	private Long id;
	/**客户id*/
	private Integer customerId;
	/**活动类型表id*/
	private Integer rechargeType;
	/**客户账户金额*/
	private Double money;
	/**客户充值金额*/
	private Double RechargeAmount;
	/**赠送金额*/
	private Double presentedAmount;
	/**充值的次数*/
	private Integer payTimes;
	/**上次充值时间*/
	private Date lastPayTime;
	private Date gmtCreate;
	private Date gmtModified;
}