package com.vs.vision.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.Expert;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.service.ExpertService;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

@Controller
public class ExpertController {
	@Autowired
	private ExpertService expertService;
	
	/**ztree树显示*/
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public Node[] doFindZTreeNodes(){
		Node[] selectExpName = expertService.selectExpName();
		
		return selectExpName;
	}
	
	
	/**远程诊断分页*/
	@RequestMapping("doLimitExp")
	@ResponseBody
	public PageObject<Expert> doLimitExp(@RequestBody Map map){
		//System.out.println("1111");
		String expertName = (String)map.get("expertName");
		Integer pageCurrent = (Integer)map.get("pageCurrent");
		//System.out.println(customerName);
		//System.out.println(pageCurrent);
		PageObject<Expert> pageObject=
				expertService.limitExp(expertName,
						pageCurrent);
		//System.out.println(pageObject.getRowCount());
		return pageObject;
	}
	
	
	/**专家表修改通过id显示数据*/
	@RequestMapping("doSelectExp")
	@ResponseBody
	public Expert doSelectExp(@RequestBody Integer id) {
		Expert entity = expertService.doSelectExp(id);
		return entity;
	}
	
	
	/**修改数据*/
	@RequestMapping("doUpdateExp")
	@ResponseBody
	public Integer doUpdateExp(@RequestBody Expert entity) {
		return expertService.doUpdateExp(entity);
	}
	
	
	/**删除*/
	@RequestMapping("doDeleteExp")
	@ResponseBody
	public Integer doDelete(@RequestBody Integer... ids) {
		return expertService.doDeleteExp(ids);
	}
	
	
	/**添加*/
	@RequestMapping("doInsertExp")
	@ResponseBody
	public Integer doInsertExp(@RequestBody Expert entity) {
		return expertService.doSaveObject(entity);
	}
	
	
	@RequestMapping("doUpdateMessage")
	@ResponseBody
	public Integer doUpdateMessage(@RequestBody Expert entity) {
		return expertService.doUpdateMessage(entity);
	}
}
