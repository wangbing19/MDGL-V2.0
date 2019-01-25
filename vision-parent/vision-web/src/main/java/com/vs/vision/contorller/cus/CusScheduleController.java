package com.vs.vision.contorller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/schedule")
public class CusScheduleController {
	
	private static final String provider_url = "http://localhost:8022";

	@Autowired
	private RestTemplate restTemplate;
	
	//转向课程页面
	@RequestMapping("/doScheduleListUI")
	public String doScheduleListUI() {
		return "/pages/sys/cusSchedule_list";
	}
	
}
