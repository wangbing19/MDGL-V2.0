package com.vs.cus.service;

import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusConsultationVo;
import com.vs.vision.vo.PageObject;

import java.util.Map;

public interface CusConsultationService {

	
    /**基于用户/电话及当前页码值条件查询用户信息*/
	PageObject<CusConsultation> findPageObjects(CusConsultationVo cusConsultationVo);
}
