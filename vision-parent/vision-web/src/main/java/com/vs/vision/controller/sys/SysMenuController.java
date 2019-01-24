package com.vs.vision.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.sys.Menus;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/menu")
public class SysMenuController {
	private static final String sys_url = "http://localhost:8029/menu";
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("doMenuListUI.do")
	public String doMenuListUI() {
		return "pages/sys/sys_menu_list";
	}

	@RequestMapping("doMenuEditUI.do")
	public String doMenuEditUI() {
		return "pages/sys/sys_menu_edit";
	}

	@RequestMapping("doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(Menus entity) {
		return restTemplate.postForObject(sys_url+"/doUpdateObject", entity, JsonResult.class);
	}

	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Menus entity) {
		return restTemplate.postForObject(sys_url+"/doSaveObject", entity, JsonResult.class);
	}

	@RequestMapping("doFindZtreeMenuNodes.do")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		return restTemplate.getForObject(sys_url + "/doFindZtreeMenuNodes", JsonResult.class);
	}

	@RequestMapping("doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		return restTemplate.postForObject(sys_url+"/doDeleteObject", id, JsonResult.class);
	}

	@RequestMapping("doFindObjects.do")
	@ResponseBody
	public JsonResult doFindObjects() {
		return restTemplate.getForObject(sys_url + "/doFindObjects", JsonResult.class);
	}

}
