package com.vs.vision.controller;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.service.RemoteDiagnoseService;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class RemoteDiagnoseController {

    @Autowired
    private RemoteDiagnoseService remoteDiagnoseService;

    @RequestMapping("/doFindPageObjects")
    public PageObject<ExpRemoteDiagnoseVo> doFindPageObjects(@RequestBody Map map){
        System.out.println("1111");
        String customerName = (String)map.get("customerName");
        Integer pageCurrent = (Integer)map.get("pageCurrent");
        System.out.println(customerName);
        System.out.println(pageCurrent);
        PageObject<ExpRemoteDiagnoseVo> pageObject=
                remoteDiagnoseService.findPageObjects(customerName,
                        pageCurrent);
        return pageObject;
    }
}
