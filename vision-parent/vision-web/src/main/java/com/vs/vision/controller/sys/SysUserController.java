package com.vs.vision.controller.sys;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.sys.Users;
import com.vs.vision.shiro.ShiroUserRealm;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;

@Controller
@RequestMapping("/user")
@PropertySource("classpath:/url.properties")
public class SysUserController {
	@Value("${sys_user_url}")
	private String sys_url;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping("doUserListUI.do")
	public String doUserListUI() {
		return "pages/sys/sys_user_list";
	}

	@RequestMapping("doUserEditUI.do")
	public String doUserEditUI() {
		return "pages/sys/sys_user_edit";
	}

	

	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setUser(ShiroUtils.getUser());
		restTemplateParmas.setName(username);
		restTemplateParmas.setPageCurrent(pageCurrent);
		return restTemplate.postForObject(sys_url + "/doFindPageObjects", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("doSearchPageObjects.do")
	@ResponseBody
	public JsonResult searchPageObjects(String username, Integer pageCurrent) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setName(username);
		restTemplateParmas.setPageCurrent(pageCurrent);
		return restTemplate.postForObject(sys_url + "/doSearchPageObjects", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("doValidById.do")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid) {
		String username = ShiroUtils.getUser().getUsername();
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setId(id);
		restTemplateParmas.setValid(valid);
		restTemplateParmas.setName(username);
		return restTemplate.postForObject(sys_url + "/doValidById", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("doFindZTreeNodes.do")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		return restTemplate.getForObject(sys_url + "/doFindZTreeNodes", JsonResult.class);
	}

	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Users Users) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setUser(ShiroUtils.getUser());
		restTemplateParmas.setUserentity(Users);
		System.out.println(Users);
		return restTemplate.postForObject(sys_url + "/doSaveObject", restTemplateParmas, JsonResult.class);
	}

	@RequestMapping("doFindObjectById.do")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		return restTemplate.postForObject(sys_url + "/doFindObjectById", id, JsonResult.class);
	}

	@RequestMapping("doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(Users Users) {
		Users.setModifiedUser(ShiroUtils.getUser().getUsername());
		return restTemplate.postForObject(sys_url + "/doUpdateObject", Users, JsonResult.class);
	}

	private AtomicInteger counter = new AtomicInteger(0);

	@RequestMapping("doLogin.do")
	@ResponseBody
	public JsonResult doLogin(String username, String password) {
		// 1.封装用户信息
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 2.提交用户信息
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);// 提交给SecurityManager
		int count = counter.incrementAndGet();// count+1;
		System.out.println("在线人数:" + count);
		return JsonResult.build(200, "登陆成功");
	}
	

}
