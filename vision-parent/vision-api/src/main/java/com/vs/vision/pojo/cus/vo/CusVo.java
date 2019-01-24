package com.vs.vision.pojo.cus.vo;

import lombok.Data;

@Data
public class CusVo {

	/**姓名*/
	private String name;
	/**电话*/
	private String tel;
	/**起始页码值*/
	private Integer pageCurrent;
	 /**门店id*/
    private int userId;
    /**上级门店id*/
    private int userParentId;
	
}
