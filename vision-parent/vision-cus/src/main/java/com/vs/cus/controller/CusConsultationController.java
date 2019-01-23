package com.vs.cus.controller;

import com.vs.cus.service.CusConsultationService;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    @Autowired
    private CusConsultationService cusConsultationService;

    /**基于用户/电话及当前页码值条件查询用户信息*/
    @RequestMapping("/findPageObjects")
    public PageObject<CusConsultation> findPageObjects(Map<String,String> map){
        return cusConsultationService.findPageObjects(map);
    }

}
