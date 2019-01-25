package com.vs.rec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.rec.service.ActivityPushService;
import com.vs.vision.pojo.rec.RecActivityPush;

@Controller
@RequestMapping("/activity")
public class ActivityPushController {
	@Autowired
	private ActivityPushService activityPushService;
	@RequestMapping("/FindPageObjects")
	@ResponseBody
	public List<RecActivityPush> findObjects(Integer activityState){
		System.out.println("后台查询数据初始化页面");
		return activityPushService.findObjects(activityState);
	}
	
	@RequestMapping("/deleteObjectById")
	@ResponseBody
	public String deleteObjectById(Integer id) {
		System.out.println("后台准备删除数据"+id);
		return activityPushService.deleteObjectById(id);
		 
	}
	
}
