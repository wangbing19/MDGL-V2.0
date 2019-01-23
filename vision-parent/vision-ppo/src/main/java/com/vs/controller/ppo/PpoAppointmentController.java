package com.vs.controller.ppo;


import com.vs.service.ppo.PpoAppointmentservice;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointment")
public class PpoAppointmentController {
    @Autowired
    private PpoAppointmentservice ppoAppointmentservice;

    @RequestMapping("/doAppointmentUI.do")
    public String doAppointmentUI(){
        PpoAppointment ppoAppointment = new PpoAppointment();
        return "/pages/sys/appointment_list";
    }

    @RequestMapping("doFindPageObjects.do")
    @ResponseBody
    public SysResult doFindPageObjects(String appointmentName, Integer pageCurrent) {

        return null;


    }
}
