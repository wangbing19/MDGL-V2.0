package com.vs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.sys.service.UserService;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestBody RestTemplateParmas RestTemplateParmas) {
		String username = RestTemplateParmas.getName();
		Integer pageCurrent = RestTemplateParmas.getPageCurrent();
		return new JsonResult(userService.findPageObjects(username, pageCurrent));
	}

	@RequestMapping("doSearchPageObjects")
	@ResponseBody
	public JsonResult searchPageObjects(@RequestBody RestTemplateParmas RestTemplateParmas) {
		String username = RestTemplateParmas.getName();
		Integer pageCurrent = RestTemplateParmas.getPageCurrent();
		return new JsonResult(userService.searchPageObjects(username, pageCurrent));
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(@RequestBody RestTemplateParmas RestTemplateParmas) {
		Integer id = RestTemplateParmas.getId();
		Integer valid = RestTemplateParmas.getValid();
		userService.doValidById(id, valid);
		return JsonResult.oK();
	}

	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		return new JsonResult(userService.findZTreeNodes());
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(@RequestBody Users Users) {
		userService.doSaveObject(Users);
		return JsonResult.oK();
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(@RequestBody Integer id) {
		Users doFindObjectById = userService.doFindObjectById(id);
		return new JsonResult(doFindObjectById);
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(@RequestBody Users Users) {
		userService.doUpdateObject(Users);
		return JsonResult.oK();
	}

}
