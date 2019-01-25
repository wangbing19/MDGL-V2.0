package com.vs.vision.contorller.ppo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.util.StringUtils;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.pojo.ppo.PpoAppointmentTime;
import com.vs.vision.pojo.ppo.vo.PpoAppTime;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/appointment")
public class PpoAppointmentTimeController {

	private static final String provider_url = "http://localhost:8025";
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping("/doappointmentTimeUI")
	public String  doappointmentTimeUI() {
		
		return "/pages/sys/appointmentTime_edit";
	}
	
	@RequestMapping("/dosaveAppointmentTime")
	@ResponseBody
	public JsonResult doinsertAppointmentTime(PpoAppTime ppoAppTime) {
		try {
			//System.out.println(ppoAppTime.getStartTime());
		//	System.out.println(ppoAppTime.getEndTime());
			if(StringUtils.isEmpty(ppoAppTime.getStartTime()) || StringUtils.isEmpty(ppoAppTime.getEndTime()))
				return JsonResult.build(201, "时间设置不正确");
			Integer result = restTemplate.postForObject(provider_url+"/saveTime", ppoAppTime, Integer.class);
			if(result != 0 || result!=null) {
				return JsonResult.oK("保存成功"); 
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存失败");
	}
	
	@RequestMapping("/dofindappointmentTime")
	@ResponseBody
	public JsonResult dofindappointmentTime(PpoAppTime ppoAppTime) {
		try {
			 @SuppressWarnings("unchecked")
			List<PpoAppTime> result = restTemplate.postForObject(provider_url+"/findTime", ppoAppTime, List.class);
			if(result!=null) {
				return JsonResult.oK(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败");
	}
}
