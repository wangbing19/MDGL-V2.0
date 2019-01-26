package com.vs.ppo.controller;




import com.vs.ppo.service.PpoAppointmentservice;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PpoAppointmentController {
	
    @Autowired
    private PpoAppointmentservice ppoAppointmentservice;


    /**
     * 查询所有训练师记录
     * @param map
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public PageObject<PpoAppointment> doFindPageObjects(@RequestBody Map map) {
	   String appointmentName=(String)map.get("appointmentName");
	   Integer pageCurrent =(Integer)map.get("pageCurrent");
		/*
		 * System.out.println("111111111"+appointmentName);
		 * System.out.println("22222222222"+pageCurrent);
		 */
          
               PageObject<PpoAppointment> pageObject=ppoAppointmentservice.doFindPageObjects(appointmentName,pageCurrent);
               try {
            	   return pageObject;
			} catch (Exception e) {
				e.printStackTrace();
			}
               return null;
            
    }
    
    /**
     * 添加训练师
     * @param ppoAppointment
     * @return
     */
    @RequestMapping("/saveAll")
    @ResponseBody
    public JsonResult doinsertAppointment(@RequestBody PpoAppointment ppoAppointment ) {
    	try {
    		ppoAppointmentservice.saveppoAppointmentAll(ppoAppointment);
			return JsonResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return JsonResult.build(201, "保存失败");
    }
    /**
     * 通过训练师ID查询该训练师记录
     * @return
     */
    @RequestMapping("findAppointmentById")
    @ResponseBody
   public PpoAppointment findAppointmentById(@RequestBody PpoAppointment ppoAppointment) {
    	try {
    	PpoAppointment appointment = ppoAppointmentservice.findAppointmentById(ppoAppointment);
    		return appointment;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
   }
  
    /**
     * 修改训练师的信息
     * @param ppoAppointment
     * @return
     */
    @RequestMapping("/updateOne")
    @ResponseBody
   public Integer doupdateAppointment(@RequestBody PpoAppointment ppoAppointment) {
	   try {
		   Integer result=   ppoAppointmentservice.doupdateAppointment(ppoAppointment);
		   return result; 
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return null;
   } 
    
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Integer dodeleteAppointment(@RequestBody PpoAppointment ppoAppointment) {
    	try {
    		Integer result=ppoAppointmentservice.dodeleteAppointment(ppoAppointment);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
