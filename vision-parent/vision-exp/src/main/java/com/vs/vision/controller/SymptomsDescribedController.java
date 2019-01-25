package com.vs.vision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.pojo.exp.SymptomsDescribed;
import com.vs.vision.service.SymptomsDescribedService;

@Controller
public class SymptomsDescribedController {
	
	@Autowired
	private SymptomsDescribedService symptomsDescribedService;
	
	/**通过id查询症状描述表中的数据*/
	@RequestMapping("doSelectSym")
	@ResponseBody
	public SymptomsDescribed selectSym(@RequestBody Integer id) {
		return symptomsDescribedService.selectSym(id);
	}
	
	
	/**症状描述表添加数据*/
	@RequestMapping("doInsertSym")
	@ResponseBody
	public Integer doInsertSym(@RequestBody SymptomsDescribed entity) {
		return symptomsDescribedService.doInsertSym(entity);
	}
	
	
	/**症状描述表修改数据*/
	@RequestMapping("doUpdateSym")
	@ResponseBody
	public Integer doUpdateSym(@RequestBody SymptomsDescribed entity) {
		return symptomsDescribedService.doUpdateSym(entity);
	}
}
