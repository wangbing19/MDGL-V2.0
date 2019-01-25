package com.vs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.sys.service.LogService;
import com.vs.vision.pojo.sys.Logs;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;
import com.vs.vision.vo.sys.RestTemplateParmas;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	private LogService logService;

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@PostMapping("doDeleteObjects") // 只能处理post请求
	@ResponseBody
	public JsonResult doDeleteObjects(@RequestBody Integer[] ids) {// spring mvc请求参数映射
		logService.deleteObjects(ids);
		return new JsonResult().oK();
	}
	
	@PostMapping("doInsertObjects") // 只能处理post请求
	@ResponseBody
	public JsonResult doInsertObjects(@RequestBody Logs log) {// spring mvc请求参数映射
		logService.insertObject(log);
		return new JsonResult().oK();
	}

	/**
	 * 查询数据
	 * 
	 * @param username
	 * @param page
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestBody RestTemplateParmas RestTemplateParmas) {
		String username = RestTemplateParmas.getName();
		Integer pageCurrent = RestTemplateParmas.getPageCurrent();
		PageObject<Logs> pageObject = logService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}

}
