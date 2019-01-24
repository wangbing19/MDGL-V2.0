package com.vs.pre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.pre.service.DiagnosisUserService;
import com.vs.vision.pojo.pre.DiagnosisUser;

@Controller
@RequestMapping("/diagnosisUser")
public class DiagnosisUserController {
	@Autowired
	private DiagnosisUserService diagnosisUserService;
	@RequestMapping("/findUserIdIsExiste")
	@ResponseBody
	public DiagnosisUser findUserIdIsExiste(Integer userId) {
		System.out.println("后台准备查询用户关系表中是否存在用户："+userId);
		return diagnosisUserService.findUserIdIsExiste(userId);
	}
	@RequestMapping("/deleteDescObjectByUserId")
	@ResponseBody
	public String deleteDescObjectByUserId(Integer userId) {
		System.out.println("后台准备删除用户症状关系表数据"+userId);
		return diagnosisUserService.deleteDescObjectByUserId(userId);
	} 
	
	@RequestMapping("/insertUserDiagbosisObject")
	@ResponseBody
	public String insertUserDiagbosisObject(Integer userId,Integer diagnosisId) {
		System.out.println("点击下载按钮准备新增用户症状关系表：userid:"+userId+"diagnosisId:"+diagnosisId);
		return diagnosisUserService.insertUserDiagbosisObject(userId,diagnosisId);
	}
	
	@RequestMapping("/updateUserDiagbosisObject")
	@ResponseBody
	public String updateUserDiagbosisObject(Integer userId,Integer diagnosisId) {
		System.out.println("点击下载按钮准备新增用户症状关系表：userid:"+userId+"diagnosisId:"+diagnosisId);
		return diagnosisUserService.updateUserDiagbosisObject(userId,diagnosisId);
	}
}
