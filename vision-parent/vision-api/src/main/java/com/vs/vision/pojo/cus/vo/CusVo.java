package com.vs.vision.pojo.cus.vo;

import lombok.Data;

@Data
public class CusVo {

	/**id*/
	private int id;
	/**状态state*/
	private int state;
	/**姓名*/
	private String name;
	/**电话*/
	private String tel;
	/**起始页码值*/
	private Integer pageCurrent;
	 /**门店id*/
    private Integer userId;
    /**上级门店id*/
    private Integer userParentId;
    /**登录用户*/
    private String user;
    
	
}
