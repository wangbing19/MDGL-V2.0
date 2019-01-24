package com.vs.vision.pojo.exp;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 对应远程诊断门店表
 * @author tarena
 *
 */
@Data
@Accessors(chain=true)
@TableName("exp_remote_diagnose")
public class RemoteDiagnose {
    /**远程门店诊断表序号*/
    private Integer id;
    /**门店地址*/
    private String deptSite;
    /**门店名称*/
    private String deptName;
    /**客户姓名*/
    private String customerName;
    /**客户电话*/
    private String customerTel;
    /**专家表序号*/
    private Integer expertId;
    /**专家回复状态0表示未回复,1表示已回复*/
    private int valid ;
    /**第几次远程诊断*/
    private Integer timeNumber;
    /**发送人姓名*/
    private String sendName;
    /**发送人电话*/
    private String sendTel;
    /**创建时间*/
    private Date gmtCreate;
    /**修改时间*/
    private Date gmtModified;
    /**登录用户账号*/
    private String registerUser;
    /**门店账号对应的父级账号id*/
    private Integer registerParentid;
    /**修改时用户的账号*/
    private String modifiedUser;
}
