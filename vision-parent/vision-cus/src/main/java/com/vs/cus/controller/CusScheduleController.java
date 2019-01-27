package com.vs.cus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.cus.service.CusScheduleService;
import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.JsonResult;
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
	
	/**基于id删除课程信息*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public Integer deleteObject(@RequestBody Integer id) {
		try {
			return cusScheduleService.deleteObject(id);
		} catch (Exception e) {
			System.out.println("基于id删除课程信息=============错误=================");
		}
		return null;
	}
	
	/**基于id查询课程信息*/
	@RequestMapping("/findObjectById")
	@ResponseBody
	public CusSchedule findObjectById(@RequestBody Integer id) {
		try {
			return cusScheduleService.findObjectById(id);
		} catch (Exception e) {
			System.out.println("基于id查询课程信息=============错误=================");
		}
		return null;
	}
	
	/**创建客户课程表*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public Integer saveObject(@RequestBody CusSchedule cusSchedule) {
		try {
			return cusScheduleService.saveObject(cusSchedule);
		} catch (Exception e) {
			System.out.println("创建客户课程表=============错误=================");
		}
		return null;
	}
	
	/**修改课程表数据*/
	@RequestMapping("/updateObject")
	@ResponseBody
	public Integer updateObject(@RequestBody CusSchedule cusSchedule) {
		try {
			return cusScheduleService.updateObject(cusSchedule);
		} catch (Exception e) {
			System.out.println("修改课程表数据=============错误=================");
		}
		return null;
	}
	
	/**基于客户id查询用户课程表信息*/
	@RequestMapping("/findByCustomerId")
	@ResponseBody
	public List<CusSchedule> findByCustomerId(@RequestBody Integer id) {
		try {
			return cusScheduleService.findByCustomerId(id);
		} catch (Exception e) {
			System.out.println("基于客户id查询用户课程表信息=============错误=================");
		}
		return null;
	}
	
}
