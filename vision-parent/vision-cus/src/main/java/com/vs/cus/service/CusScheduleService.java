package com.vs.cus.service;

import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

public interface CusScheduleService {

	/**基于用户/电话及当前页码值条件查询课程信息*/
	PageObject<CusSchedule> findPageObjects(CusVo cusVo);
}
