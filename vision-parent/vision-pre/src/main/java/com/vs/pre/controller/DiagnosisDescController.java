package com.vs.pre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.pre.service.DiagnosisDescService;
import com.vs.vision.pojo.pre.DiagnosisDesc;

@Controller
@RequestMapping("/diagnosisDesc")
public class DiagnosisDescController {
	@Autowired
	private DiagnosisDescService diagnosisDescService;
	@RequestMapping("/findDescObjectById")
	@ResponseBody
	public DiagnosisDesc findDescObjectById(Integer diagnosisId) {
		System.out.println("后台收到查询症状描述的id："+diagnosisId);
		DiagnosisDesc descObject = diagnosisDescService.findDescObjectById(diagnosisId);	
		return descObject;
	}
}
