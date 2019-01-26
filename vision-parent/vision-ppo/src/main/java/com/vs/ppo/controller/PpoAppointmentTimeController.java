package com.vs.ppo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.ppo.service.PpoAppointmentTimeservice;
import com.vs.vision.pojo.ppo.vo.PpoAppTime;

@Controller
public class PpoAppointmentTimeController {
	@Autowired
	private PpoAppointmentTimeservice ppoAppointmentTimeservice;
	/**
	 * 保存训练师时间
	 * @param ppoAppTime
	 * @return
	 */
	@RequestMapping("/saveTime")
	@ResponseBody
	public Integer saveTime(@RequestBody PpoAppTime ppoAppTime) {
		try {
			Integer result=ppoAppointmentTimeservice.saveTime(ppoAppTime);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改训练师时间
	 * @param ppoAppTime
	 * @return
	 */
	@RequestMapping("/UpdateTime")
	@ResponseBody
	public Integer doupdateappointmentTime(@RequestBody PpoAppTime ppoAppTime) {
		
		try {
			Integer result= ppoAppointmentTimeservice.updateTime(ppoAppTime);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过id查询该训练师的训练时间
	 * @param ppoAppTime
	 * @return
	 */
	@RequestMapping("/findTime")
	@ResponseBody
	public List<PpoAppTime> findappointmentTime(@RequestBody PpoAppTime ppoAppTime) {
		
		try {
			List<PpoAppTime> result= ppoAppointmentTimeservice.findTime(ppoAppTime);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
