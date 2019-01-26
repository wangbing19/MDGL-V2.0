package com.vs.vision.contorller.rec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.rec.RecActivityPush;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/activity")
public class WebRecActivityPush {
	@Autowired
	private RestTemplate restTemplate;
	public static final String local_url = "http://localhost:8027";
	@RequestMapping("doLoadActivityUI.do")
	public String doLoadActivityUI() {
		return "pages/sys/rec_activity_push";
	}
	
	@RequestMapping("/doSaveOrUpdateObject")
	public String doSaveOrUpdateObject() {
		return "pages/sys/rec_save_update";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer activityState) {
		System.out.println("前台请求查询充值类型数据初始化页面：");
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("activityState", activityState);
		String url = local_url+"activity/FindPageObjects";
		List<RecActivityPush> findObjects = restTemplate.postForObject(url,map,List.class);
		return JsonResult.oK(findObjects);
	}
	
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		System.out.println("前台请求删除重铸类型:"+id);
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("id", id);
		String url = local_url+"activity/deleteObjectById";
		String deleteMessage = restTemplate.postForObject(url,map,String.class);
		return JsonResult.oK(deleteMessage);		
	}
	
	@RequestMapping("/doInsertRecActivityObject.do")
	@ResponseBody
	public JsonResult doInsertRecActivityObject(RecActivityPush recActivityPush) {
		System.out.println("前台请求新增充值类型："+recActivityPush);
		String url = local_url+"activity/insertRecActivityObject";
		String insertMessage = restTemplate.postForObject(url,recActivityPush,String.class);
		return JsonResult.oK(insertMessage);
	}
	
	@RequestMapping("/doUpdateRecActivityObject.do")
	@ResponseBody
	public JsonResult doUpdateRecActivityObject(RecActivityPush recActivityPush) {
		String url = local_url+"activity/updateRecActivityObject";
		String updateMessage = restTemplate.postForObject(url,recActivityPush,String.class);
		return JsonResult.oK(updateMessage);
	}
	
}





























