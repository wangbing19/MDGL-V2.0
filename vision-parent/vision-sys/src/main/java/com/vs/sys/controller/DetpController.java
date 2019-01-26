package com.vs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vs.sys.service.DeptService;
import com.vs.vision.pojo.sys.Depts;
import com.vs.vision.vo.JsonResult;

@RestController
@RequestMapping("/dept")
public class DetpController {
	@Autowired DeptService sysDeptService;
	
	/**
	 * 页面初始化数据
	 * @return
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysDeptService.findObjects());
	}

	/**
	 * 查询组织结构数据
	 * @return
	 */
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		return new JsonResult(sysDeptService.findZTreeNodes());
	}
	/**
	 * 新增保存
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(@RequestBody Depts entity){
		sysDeptService.saveObject(entity);
		return JsonResult.oK();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(@RequestBody Depts entity){
		sysDeptService.updateObject(entity);
		return JsonResult.oK();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(@RequestBody Integer id){
		sysDeptService.deleteObject(id);
		return JsonResult.oK();
	}
}
