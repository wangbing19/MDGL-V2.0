package com.vs.cus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.cus.service.CusDiagnoseService;
import com.vs.vision.pojo.cus.CusDiagnose;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/cusDiagnose")
public class CusDiagnoseController {
	
	@Autowired
	private CusDiagnoseService cusDiagnoseService;
	
	/**诊断表页面加载,查询*/
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public PageObject<CusDiagnose> findPageObjects(@RequestBody CusVo cusVo){
		try {
			return cusDiagnoseService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("诊断表页面加载,查询=============错误=================");
		}
		return null;
	}
	
	/**基于咨询表id,查询相关id所有信息*/
	@RequestMapping("/findObjectById")
	@ResponseBody
	public CusDiagnose findObjectById(@RequestBody Integer id) {
		try {
			return cusDiagnoseService.findObjectById(id);
		} catch (Exception e) {
			System.out.println("基于咨询表id,查询相关id所有信息=============错误=================");
		}
		return null;
	}
	
	/**基于客户id查询诊断表相关信息*/
	@RequestMapping("/findByCustomerId")
	@ResponseBody
	public CusDiagnose findByCustomerId(@RequestBody Integer customerId) {
		try {
			return cusDiagnoseService.findByCustomerId(customerId);
		} catch (Exception e) {
			System.out.println("基于客户id查询诊断表相关信息=============错误=================");
		}
		return null;
	}
	
	/**基于客户id创建客户诊断表*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public Integer saveObject(@RequestBody CusDiagnose cusDiagnose) {
		try {
			return cusDiagnoseService.saveObject(cusDiagnose);
		} catch (Exception e) {
			System.out.println("基于客户id创建客户诊断表=============错误=================");
		}
		return null;
	}
	
	/**基于诊断表id删除数据*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public Integer deleteObject(@RequestBody Integer id) {
		try {
			return cusDiagnoseService.deleteObject(id);
		} catch (Exception e) {
			System.out.println("基于诊断表id删除数据=============错误=================");
		}
		return null;
	}
	
	
}
