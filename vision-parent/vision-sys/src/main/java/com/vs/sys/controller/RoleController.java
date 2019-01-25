package com.vs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.sys.service.RoleService;
import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;
import com.vs.vision.vo.sys.SysRoleMenuResult;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;


	@RequestMapping("/doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(roleService.findObjects());
	}

	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(@RequestBody Integer id) {
		SysRoleMenuResult findObjectById = roleService.findObjectById(id);
		return new JsonResult(findObjectById);
	}

	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(@RequestBody RestTemplateParmas RestTemplateParmas) {
		Roles entity = RestTemplateParmas.getRole();
		Integer[] menuIds =RestTemplateParmas.getIds();
		roleService.updateObject(entity, menuIds);
		return new JsonResult().oK();
	}

	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(@RequestBody RestTemplateParmas RestTemplateParmas) {
		Roles entity = RestTemplateParmas.getRole();
		Integer[] menuIds =RestTemplateParmas.getIds();
		roleService.saveObject(entity, menuIds);
		return new JsonResult().oK();
	}

	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(@RequestBody Long id) {
		roleService.deleteObject(id);
		return new JsonResult().oK();
	}

	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestBody RestTemplateParmas RestTemplateParmas) {
		String name = RestTemplateParmas.getName();
		Integer pageCurrent = RestTemplateParmas.getPageCurrent();
		return new JsonResult(roleService.findPageObjects(name, pageCurrent));
	}

}
