package com.vs.cus.controller;

import com.vs.cus.service.CusConsultationService;
import com.vs.vision.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    @Autowired
    private CusConsultationService cusConsultationService;

    @RequestMapping("/findPageObjects")
    public JsonResult findPageObjects(Map<String,String> map){
        return cusConsultationService.findPageObjects(map);
    }

}
