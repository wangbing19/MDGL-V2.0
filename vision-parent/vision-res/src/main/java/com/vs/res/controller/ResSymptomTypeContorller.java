package com.vs.res.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vs.res.servise.ResSymptomTypeSvervise;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.res.ResSymptomType;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/symptomType")
public class ResSymptomTypeContorller {
	@Autowired
	ResSymptomTypeSvervise resSymptomTypeSvervise;
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<ResSymptomType> findObjects(@RequestBody Integer userId){
		try {
			List<ResSymptomType> result=resSymptomTypeSvervise.findObjects(userId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**课程表查询咨询表所有信息*/
	@RequestMapping("/findSymptomType")
	@ResponseBody
	public List<ResSymptomType> findSymptomType(@RequestBody Integer userId){
		try {
			return resSymptomTypeSvervise.findSymptomType(userId);
		} catch (Exception e) {
			System.out.println("课程表查询咨询表所有信息==================错误=============");
		}
		return null;
	}
	
	@RequestMapping("dofindPageObject")
	@ResponseBody
	public ResSymptomType dofindPageObjectOne(@RequestBody ResSymptomType resSymptomType) {
		try {
			
			ResSymptomType result=resSymptomTypeSvervise.findPageObjectOne(resSymptomType);
			return result;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修该资源配置
	 * @param resSymptomType
	 * @return
	 */
	@RequestMapping("doupdateObject")
	@ResponseBody
	public Integer doupdateObject(@RequestBody ResSymptomType resSymptomType) {
		try {
			Integer result=resSymptomTypeSvervise.doupdateObject(resSymptomType);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 * 根据id删除资源配置信息
	 */
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public Integer doDeleteObject(@RequestBody ResSymptomType resSymptomType) {
		try {
			Integer result=resSymptomTypeSvervise.doDeleteObject(resSymptomType);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
	
}
