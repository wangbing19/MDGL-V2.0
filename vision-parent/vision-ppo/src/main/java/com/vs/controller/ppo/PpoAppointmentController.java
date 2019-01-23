package com.vs.controller.ppo;


import com.vs.service.ppo.PpoAppointmentservice;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointment")
public class PpoAppointmentController {
    @Autowired
    private PpoAppointmentservice ppoAppointmentservice;


    /**
     * 用于测试
     * @return
     */
    @RequestMapping("/doAppointmentUI.do")
    public String doAppointmentUI(){

        return "/pages/sys/appointment_list";
    }


   @RequestMapping("doFindPageObjects.do")
    @ResponseBody
    public JsonResult doFindPageObjects(String appointmentName, Integer pageCurrent) {
            try {
               PageObject<PpoAppointment> pageObject=ppoAppointmentservice.doFindPageObjects(appointmentName,pageCurrent);
               return JsonResult.oK(pageObject);
            }catch (Exception e){
                e.printStackTrace();
            }
        return JsonResult.format("查询失败！");


    }
}
