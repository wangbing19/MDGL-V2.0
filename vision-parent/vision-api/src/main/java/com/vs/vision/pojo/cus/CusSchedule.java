package com.vs.vision.pojo.cus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)    //开启链式结构
@TableName("cus_schedule")
public class CusSchedule {

    @TableId(type = IdType.AUTO)
    private int id; /**id*/
    /**课程名称*/
    private String courseTitle;
    /**课程价格*/
    private int priceOfCourse;
    /**课程训练数*/
    private int courseTraining;
    /**课程数*/
    private int numberOfCourse;
    /**总价格*/
    private int totalPrice;
    /**总训练数*/
    private int sum;
    /**训练项目*/
    private String trainingSession;
    /**服务时间*/
    private String servicingTime;
    /**消费时间*/
    private Date consumptionTime;
    /**建表时间*/
    private Date gmtCreate;
    /**修改时间*/
    private Date gmtModified;
    /**客户id*/
    private int customerId;

}
