package com.vs.vision.pojo.cus.vo;

import lombok.Data;

@Data
public class CusConsultationVo {

	private String name;
	private String tel;
	private Integer pageCurrent;
	 /**门店id*/
    private int userId;
    /**上级门店id*/
    private int userParentId;
	
}
