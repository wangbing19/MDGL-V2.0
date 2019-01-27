package com.vs.vision.contorller.ppo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/appointment")
public class PpoAppointmentController {

	private static final String provider_url = "http://localhost:8025";
	@Autowired
	private RestTemplate restTemplate;

	
	
	/**
	 * 加载训练师页面
	 * @return
	 */
	@RequestMapping("/doAppointmentUI")
	public String doAppointmentUI(){

		return "/pages/sys/appointment_list";
	}

	
	
	/**
	 * 查询所有训练师
	 * @param appointmentName
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult findAll(String appointmentName,Integer pageCurrent) {
		Map<String,Object> map= new HashMap<>();
		map.put("appointmentName", appointmentName);
		map.put("pageCurrent", pageCurrent);

		PageObject postForObject = restTemplate.postForObject(provider_url+"/findAll", map, PageObject.class);
		System.out.println(postForObject);
		try {
			return JsonResult.oK(postForObject);
		} catch (Exception e) {
			System.out.println("前端错误----------------------------------------------");
		}
		return null;
	}
	
	
	
	/**
	 * 实现添加训练师页面跳转
	 * @return
	 */
	@RequestMapping("saveObjectUI")
	public String saveObjectUI() {

		return "/pages/sys/appointment_edit";
	}

	
	/**
	 *实现训练师添加
	 */
	@RequestMapping("doinsertAppointment")
	@ResponseBody
	public JsonResult doinsertAppointment(PpoAppointment ppoAppointment) {
		try {
			
			 ResponseEntity<JsonResult> postForEntity = restTemplate.postForEntity(provider_url+"/saveAll", ppoAppointment, JsonResult.class);
			return JsonResult.oK(postForEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存失败");
	}
	
	/**
	 * 通过训练师Id查询训练师信息
	 * @param PpoAppointment
	 * @return
	 */
	@RequestMapping("findAppointmentById")
	@ResponseBody
	public JsonResult findAppointmentById(PpoAppointment ppoAppointment) {
		try {
			PpoAppointment postForObject = restTemplate.postForObject(provider_url+"/findAppointmentById", ppoAppointment, PpoAppointment.class);
			return JsonResult.oK(postForObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败");
	}
	
	
	/**
	 * 修改训练师
	 * 
	 */
	@RequestMapping("doupdateAppointment")
	@ResponseBody
	public JsonResult doupdateAppointment(PpoAppointment ppoAppointment) {
		try {
			Integer result = restTemplate.postForObject(provider_url+"/updateOne", ppoAppointment, Integer.class);
			if(result !=0 || result !=null) {
				return JsonResult.oK("修改成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败");
	}
	
	
	/**
	 * 删除训练师信息
	 * @param ppoAppointment
	 * @return
	 */
	@RequestMapping("/dodeleteAppointment")
	@ResponseBody
	public JsonResult dodeleteAppointment(PpoAppointment ppoAppointment) {
		try {
			Integer result = restTemplate.postForObject(provider_url+"/deleteOne", ppoAppointment, Integer.class);
			if(result !=0 || result !=null) {
				return JsonResult.oK("删除成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除失败！");
	}
	
	
	
	@RequestMapping("/getAppoName")
	@ResponseBody
	public JsonResult findAppointmentName() {
		try {
			Integer userId=0;
			List<PpoAppointment> result = restTemplate.postForObject(provider_url+"/findAppointmentName", userId, List.class);
			if(result.size() !=0 || result !=null) {
				return JsonResult.oK(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "没有训练师");
	}
	
}
