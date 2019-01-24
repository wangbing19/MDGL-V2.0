package com.vs.cus.service;

import com.vs.vision.pojo.cus.CusDiagnose;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

public interface CusDiagnoseService {

	/**诊断表页面加载,查询*/
	PageObject<CusDiagnose> findPageObjects(CusVo cusVo);
	/**基于咨询表id,查询相关id所有信息*/
	CusDiagnose findObjectById(Integer id);
}
