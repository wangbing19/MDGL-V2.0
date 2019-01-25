package com.vs.vision.contorller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/cusSchedule")
public class CusScheduleController {
	
	private static final String provider_url = "http://localhost:8022";

	@Autowired
	private RestTemplate restTemplate;
	
	/**转向课程页面*/
	@RequestMapping("/doScheduleListUI")
	public String doScheduleListUI() {
		return "/pages/sys/cusSchedule_list";
	}
	
	/**基于用户/电话及当前页码值条件查询课程信息*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(CusVo cusVo) {
		try {
			//获取登录用户id及上级id
        	cusVo.setUserId(0);
        	cusVo.setUserParentId(0);
        	PageObject<CusSchedule> pageObject = restTemplate.postForObject(provider_url+"/schedule/findPageObjects", cusVo, PageObject.class);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于id删除课程信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer rows = restTemplate.postForObject(provider_url+"/schedule/deleteObject", id, Integer.class);
			if(rows !=0 && rows !=null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该数据可能已经被删除");
	}
	
	
}
