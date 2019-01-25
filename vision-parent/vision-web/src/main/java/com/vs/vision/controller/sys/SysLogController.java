package com.vs.vision.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.annoation.RequiresLog;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;

@Controller
@RequestMapping("/log")
public class SysLogController {
	private static final String sys_url = "http://localhost:8029/log";
    @Autowired
    private RestTemplate restTemplate;
    
	/**
	 * 加载页面
	 * 
	 * @return
	 */
	@RequestMapping("doLogListUI.do")
	public String doLogListUI() {
		return "pages/sys/sys_log_list";
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@PostMapping("doDeleteObjects.do") // 只能处理post请求
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {// spring mvc请求参数映射
		return restTemplate.postForObject(sys_url+"/doDeleteObjects", ids, JsonResult.class);
	}

	/**
	 * 查询数据
	 * 
	 * @param username
	 * @param page
	 * @return
	 */
	@RequiresLog("2019日志查询")
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, @RequestParam(value = "pageCurrent", required = false) Integer pageCurrent) {
		RestTemplateParmas restTemplateParmas = new RestTemplateParmas();
		restTemplateParmas.setName(username);
		restTemplateParmas.setPageCurrent(pageCurrent);
		return restTemplate.postForObject(sys_url + "/doFindPageObjects", restTemplateParmas, JsonResult.class);
	}

}
