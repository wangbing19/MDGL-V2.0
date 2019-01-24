package com.vs.cus.controller;

import com.vs.cus.service.CusConsultationService;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusConsultationVo;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    @Autowired
    private CusConsultationService cusConsultationService;

    
    /**基于用户/电话及当前页码值条件查询用户信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public PageObject<CusConsultation> findPageObjects(@RequestBody CusConsultationVo cusConsultationVo){
    	try {
    		 return cusConsultationService.findPageObjects(cusConsultationVo);
		} catch (Exception e) {
			System.out.println("fghjkk===================================");
		}
    	
        return null;
    }

}
