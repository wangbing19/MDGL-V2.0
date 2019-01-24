package com.vs.pre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.pre.service.DiagnosisResultService;
import com.vs.vision.pojo.pre.DiagnosisResult;

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
}
