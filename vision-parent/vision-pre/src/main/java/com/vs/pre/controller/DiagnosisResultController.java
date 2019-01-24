package com.vs.pre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.pre.service.DiagnosisResultService;
import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.vo.DiagnosisDate;
import com.vs.vision.vo.Node;

@Controller
@RequestMapping("/diagnosisResult")
public class DiagnosisResultController {
	@Autowired
	private DiagnosisResultService diagnosisResultService;
	//后台查找诊断结果表所有数据，初始化表格页面数据
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public List<DiagnosisResult> doFindObjects(){
		System.out.println("我是后台controller");
		return diagnosisResultService.doFindObjects();
	}
	
	//后台根据症状id删除数据
	@RequestMapping("/deleteResultObjectById")
	@ResponseBody
	public String deleteResultObjectById(Integer diagnosisId) {
		System.out.println("后台收到需要删除的症状id:"+diagnosisId);
		String deleteMessage = diagnosisResultService.deleteResultObjectById(diagnosisId);
		return deleteMessage;
	}
	//加载ztree资源
	@RequestMapping("/findZtreeMenuNodes")
	@ResponseBody
	public List<Node> findZtreeMenuNodes(){
		System.out.println("后台controller准备加载ztree资源");
		return diagnosisResultService.findZtreeMenuNodes();
	}
	//查询父级症状的名称
	@RequestMapping("/findParentNameById")
	@ResponseBody
	public String findParentNameById(Integer id) {
		System.out.println("后台准备查询父级症状的名称："+id);
		return diagnosisResultService.findParentNameById(id);
	}
	
	//更新症状信息
	@RequestMapping("/updateObject")
	@ResponseBody
	public String updateObject(@RequestBody DiagnosisDate diagnosisDate) {
		System.out.println("后台收到症状更新的信息："+diagnosisDate);
		return diagnosisResultService.updateObject(diagnosisDate);
	}
	//根据id查询一行数据
	@RequestMapping("/findObjectById")
	@ResponseBody
	public DiagnosisResult findObjectById(Integer id) {
		System.out.println("后台需要查询主键为："+id+"的数据");
		return diagnosisResultService.findObjectById(id);
	}
	//根据id修改显示状态
	@RequestMapping("/updateDisTypeById")
	@ResponseBody
	public String updateDisTypeById(Integer disType,Integer id) {
		System.out.println("后台准备修改id为："+id+"显示状态为："+disType);
		return diagnosisResultService.updateDisTypeById(disType,id);
	}
	//新增症状信息
	@RequestMapping("/insertObject")
	@ResponseBody
	public String insertObject(@RequestBody DiagnosisResult diagnosisResult) {
		System.out.println("后台准备新增数据");
		return diagnosisResultService.insertObject(diagnosisResult);
	}
}
