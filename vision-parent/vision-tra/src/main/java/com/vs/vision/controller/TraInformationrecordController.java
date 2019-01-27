package com.vs.vision.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.service.TraInformationrecordService;
import com.vs.vision.vo.PageObject;

@Controller
public class TraInformationrecordController {
	
	@Autowired
	private TraInformationrecordService traInformationrecordService;
	
	/**分页*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public PageObject<TraInformationrecord> doFindPageObjects(@RequestBody Map map){
		System.out.println("1111");
		String name = (String)map.get("name");
		Integer pageCurrent = (Integer)map.get("pageCurrent");
		Integer userParentId = (Integer)map.get("userParentId");
		System.out.println(name);
		System.out.println(pageCurrent);
		PageObject<TraInformationrecord> pageObject=
				traInformationrecordService.findPageObjects(name,
						pageCurrent,userParentId);
		System.out.println(pageObject.getRowCount());
		return pageObject;
	}
	
	
	/**添加*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public Integer doSaveObject(@RequestBody TraInformationrecord entity) {
		return traInformationrecordService.doSaveObject(entity);
	}
	
	
	/**删除*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public Integer doDeleteObject(@RequestBody Integer id) {
		return traInformationrecordService.doDeleteObject(id);
	}
	
	
	/**通过id查询*/
	@RequestMapping("doSelectUI")
	@ResponseBody
	public TraInformationrecord doSelect(@RequestBody Integer id) {
		TraInformationrecord entity = traInformationrecordService.doSelect(id);
		return entity;
	}
	
	
	/**修改*/
	@RequestMapping("doUpdate")
	@ResponseBody
	public Integer doUpdate(@RequestBody TraInformationrecord entity) {
		return traInformationrecordService.doUpdate(entity);
	}
}
