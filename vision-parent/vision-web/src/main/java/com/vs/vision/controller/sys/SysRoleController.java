package com.vs.vision.controller.sys;

import javax.annotation.PreDestroy;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vs.vision.annoation.RequiresLog;
import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;

@Controller
@RequestMapping("/role")
@PropertySource("classpath:/url.properties")
public class SysRoleController {

	@Value("${sys_role_url}")
	private String sys_url;

	@Autowired
	private RestTemplate restTemplate;
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping("doRoleListUI.do")
	public String doRoleListUI() {
		return "pages/sys/sys_role_list";
	}

	@RequestMapping("doRoleEditUI.do")
	public String doRoleEditUI() {
		return "pages/sys/sys_role_edit";
	}

	@RequestMapping("/doFindRoles.do")
	@ResponseBody
	public JsonResult doFindObjects() {
		return restTemplate.getForObject(sys_url + "/doFindRoles", JsonResult.class);
	}

	@RequestMapping("/doFindObjectById.do")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		return restTemplate.postForObject(sys_url + "/doFindObjectById", id, JsonResult.class);
	}

	@RequestMapping("/doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(Roles entity, Integer[] menuIds) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setRole(entity);
		restTemplateParmas.setIds(menuIds);
		return restTemplate.postForObject(sys_url + "/doUpdateObject", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("/doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Roles entity, Integer[] menuIds) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setRole(entity);
		restTemplateParmas.setIds(menuIds);
		return restTemplate.postForObject(sys_url + "/doSaveObject", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("/doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Long id) {
		System.out.println(id);
		return restTemplate.postForObject(sys_url + "/doDeleteObject", id, JsonResult.class);
	}
	
	@RequiresLog("角色查询")
	@RequestMapping("/doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setName(name);
		restTemplateParmas.setPageCurrent(pageCurrent);
		return restTemplate.postForObject(sys_url + "/doFindPageObjects", restTemplateParmas, JsonResult.class);
	}

}
