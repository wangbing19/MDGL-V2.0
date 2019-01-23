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
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public List<DiagnosisResult> doFindObjects(){
		System.out.println("我是后台controller");
		return diagnosisResultService.doFindObjects();
	}
}
