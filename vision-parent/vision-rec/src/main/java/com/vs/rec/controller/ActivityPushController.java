package com.vs.rec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vs.rec.service.ActivityPushService;
import com.vs.vision.pojo.rec.RecActivityPush;

@RestController
@RequestMapping("/activity")
public class ActivityPushController {
	@Autowired
	private ActivityPushService activityPushService;
	@RequestMapping("/FindPageObjects")
	
	public List<RecActivityPush> findObjects(Integer activityState){
		System.out.println("后台查询数据初始化页面");
		return activityPushService.findObjects(activityState);
	}
	
	@RequestMapping("/deleteObjectById")
	
	public String deleteObjectById(Integer id) {
		System.out.println("后台准备删除数据"+id);
		return activityPushService.deleteObjectById(id);
		 
	}
	
	@RequestMapping("/insertRecActivityObject")
	public String insertRecActivityObject(@RequestBody RecActivityPush recActivityPush) {
		System.out.println("后台准备入库："+recActivityPush);
		return activityPushService.insertRecActivityObject(recActivityPush);
	}
	@RequestMapping("/updateRecActivityObject")
	public String updateRecActivityObject(@RequestBody RecActivityPush recActivityPush) {
		return activityPushService.updateRecActivityObject(recActivityPush);
	}
	
}
