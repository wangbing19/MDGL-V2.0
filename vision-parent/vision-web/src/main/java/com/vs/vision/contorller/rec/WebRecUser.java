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
import com.vs.vision.pojo.rec.RecPayUser;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/recUser")
public class WebRecUser {
	@Autowired
	private RestTemplate restTemplate;
	public static final String local_url = "http://localhost:8027";
	@RequestMapping("/doRecUserRecordUI.do")
	public String doRecUserRecord() {
		return "pages/sys/rec_user_record";
		
	}
	
	@RequestMapping("/doGetRecUser")
	public String doGetRecUser() {
		return "pages/sys/record_insert";
	}
	
	@RequestMapping("/doFindPageObjectsByUserId") 
	@ResponseBody
	public JsonResult doFindPageObjects() {
		System.out.println("前台请求根据门店id，父级门店id查询数据");
		Users user = ShiroUtils.getUser();
		System.out.println(user);
		String url = local_url+"/recUser/findObjectByUserIdAndParentId";
		List<RecPayUser> findObjectList = restTemplate.postForObject(url,user,List.class);
		return JsonResult.oK(findObjectList);
	}
	
	@RequestMapping("/doFindActivityObjectByUserPayType")
	@ResponseBody
	public JsonResult doFindActivityObjectByUserPayType(Integer id) {
		String url = local_url+"recUser/doFindActivityObjectByUserPayType";
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("id", id);
		System.out.println("查询标题："+id);
		RecActivityPush postForObject = restTemplate.postForObject(url,map,RecActivityPush.class);
		System.out.println("数据："+postForObject);
		return JsonResult.oK(postForObject);
	}
}
