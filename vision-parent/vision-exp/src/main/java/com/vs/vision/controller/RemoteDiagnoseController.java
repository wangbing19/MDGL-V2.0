package com.vs.vision.controller;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.service.RemoteDiagnoseService;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;


@Controller
public class RemoteDiagnoseController {

	@Autowired
	private RemoteDiagnoseService remoteDiagnoseService;

	/**远程诊断分页*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public PageObject<ExpRemoteDiagnoseVo> doFindPageObjects(@RequestBody Map map){
		System.out.println("1111");
		String customerName = (String)map.get("customerName");
		Integer pageCurrent = (Integer)map.get("pageCurrent");
		System.out.println(customerName);
		System.out.println(pageCurrent);
		PageObject<ExpRemoteDiagnoseVo> pageObject=
				remoteDiagnoseService.findPageObjects(customerName,
						pageCurrent);
		System.out.println(pageObject.getRowCount());
		return pageObject;
	}

	/**远程诊断修改*/
	@RequestMapping("doSelect")
	@ResponseBody
	public ExpRemoteDiagnoseVo doSelect(@RequestBody Integer id) {
		ExpRemoteDiagnoseVo entity = remoteDiagnoseService.select(id);
		return entity;
	}
	
	/**修改解决状态*/
	@RequestMapping("doValidById")
	@ResponseBody
	public Integer doValidById(@RequestBody Map map) {
		Integer id = (Integer)map.get("id");
		Integer valid = (Integer)map.get("valid");
		return remoteDiagnoseService.validById(id,valid);
	}
	
	/**删除*/
	@RequestMapping("doDelete")
	@ResponseBody
	public Integer doDelete(@RequestBody Integer... ids) {
		return remoteDiagnoseService.doDelete(ids);
	}
	
	/**添加*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public Integer doSaveObject(@RequestBody RemoteDiagnose entity) {
		return remoteDiagnoseService.doSaveObject(entity);
	}
	
	/**修改*/
	@RequestMapping("doUpdate")
	@ResponseBody
	public Integer doUpdate(@RequestBody RemoteDiagnose entity) {
		return remoteDiagnoseService.doUpdate(entity);
	}
}
