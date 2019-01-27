package com.vs.vision.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.sys.Menus;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/menu")
@PropertySource("classpath:/url.properties")
public class SysMenuController {
	@Value("${sys_menu_url}")
	private String sys_url;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequiresPermissions("sys:menu:view")
	@RequestMapping("doMenuListUI.do")
	public String doMenuListUI() {
		return "pages/sys/sys_menu_list";
	}
	
	@RequiresPermissions("sys:menu:add")
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
	
	@RequiresPermissions("sys:menu:delete")
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
