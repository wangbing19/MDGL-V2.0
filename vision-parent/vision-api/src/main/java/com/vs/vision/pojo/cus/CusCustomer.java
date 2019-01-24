package com.vs.vision.pojo.cus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 客户信息表
 * @author tarena
 *
 */
@Data
@Accessors(chain = true)    //开启链式结构
@TableName("cus_customer")
public class CusCustomer {

	/**充值记录数*/
	@TableField(exist=false)	//mybatis入库操作时,忽略该字段
	private int rechargeCount;
	/**课程记录数*/
	@TableField(exist=false)	//mybatis入库操作时,忽略该字段
	private int scheduleCount;
	/**上次训练时间*/
	@TableField(exist=false)	//mybatis入库操作时,忽略该字段
	private Date lastTrain;
	
    @TableId(type = IdType.AUTO)
    private int id; /**序号*/
    /**门店id*/
    private int userId;
    /**上级门店id*/
    private int userParentId;
    /**姓名*/
    private String name;
    /**性别*/
    private String gender;
    /**年龄*/
    private int age;
    /**生日*/
    private Date birthday;
    /**联系电话*/
    private String tel;
    /**状态*/
    private int state;
    /**监护人*/
    private String guardian;
    /**次监护人*/
    private String lastGuardian;
    /**次监护人电话*/
    private String lastGuardianTel;
    /**家庭住址*/
    private String home;
    /**学校地址*/
    private String school;
    /**总金额*/
    private double money;
    /**余额*/
    private double balance;
    /**总训练次数*/
    private int totalTrainingTime;
    /**已训练次数*/
    private int timesOfTraining;
    /**备注*/
    private String remark;
    /**咨询表id*/
    private int consultationId;
    /**诊断表id*/
    private int diagnoseId;
    /**建表时间*/
    private Date gmtCreate;
    /**修改时间*/
    private Date gmtModified;
    /**创建用户*/
    private String createdUser;
    /**修改用户*/
    private String modifiedUser;

}
