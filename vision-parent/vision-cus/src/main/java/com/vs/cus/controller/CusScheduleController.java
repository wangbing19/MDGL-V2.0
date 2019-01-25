package com.vs.cus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.cus.service.CusScheduleService;
import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/schedule")
public class CusScheduleController {
	
	@Autowired
	private CusScheduleService cusScheduleService;
	
	/**基于用户/电话及当前页码值条件查询课程信息*/
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public PageObject<CusSchedule> findPageObjects(@RequestBody CusVo cusVo){
		try {
			return cusScheduleService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("基于用户/电话及当前页码值条件查询课程信息=============错误=================");
		}
		return null;
	}
	
}
