package com.vs.cus.service;

import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import java.util.Map;

public interface CusConsultationService {

	
    /**基于用户/电话及当前页码值条件查询用户信息*/
	PageObject<CusConsultation> findPageObjects(CusVo cusVo);
	/**将CusCustomer类型数据添加到数据库*/
	Integer saveObject(CusConsultation cusConsultation);
	/**基于id删除咨询表信息*/
	Integer deleteObject(Integer id);
	/**基于咨询表id,查询相关id所有信息*/
	CusConsultation findObjectById(Integer id);
	/**基于咨询表id更改用户信息*/
	Integer updateObject(CusConsultation cusConsultation);
}
