package com.vs.vision.contorller.res;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

	@Controller
	@RequestMapping("/ResProjectConfig")
	public class ResProjectConfigContorller {
		
		private static final String provider_url = "http://localhost:8028";

		@Autowired
		private RestTemplate restTemplate;
		
		
		
	@RequestMapping("/doResProjectConfigUI")
	public String doResProjectConfigUI() {
		return "/pages/sys/ResProjectConfig_List";
	}
	
	/**
	 * 查询所有症状类型信息
	 * @return
	 */
	@RequestMapping("/doFingPageObject")
	@ResponseBody
	public JsonResult dofindObjects(Integer pageCurrent) {
		int userId=0;
		try {
			Map<String,Object> map= new HashMap<>();
			map.put("pageCurrent", pageCurrent);
			map.put("userId", userId);
			PageObject<ResProjectConfig> postForObject = restTemplate.postForObject(provider_url + "/ResProjectConfig/findAll", map, PageObject.class);
			return JsonResult.oK(postForObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询失败！");
	}
}
