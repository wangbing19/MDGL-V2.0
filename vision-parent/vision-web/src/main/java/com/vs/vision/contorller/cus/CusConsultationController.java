package com.vs.vision.contorller.cus;

import com.alibaba.druid.util.StringUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    private static final String provider_url = "http://localhost:8022";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("doCusConsultationListUI")
    public String doCusConsultationListUI(){
        return "pages/sys/cusConsultation_list";
    }

    /**基于用户/电话及当前页码值条件查询用户信息*/
    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult FindPageObjects(String name, String tel, Integer pageCurrent){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("tel",tel);
        map.put("pageCurrent",pageCurrent.toString());
        
        try {
        	PageObject pageObject = restTemplate.getForObject(provider_url+"/cusConsultation/findPageObjects", PageObject.class, map);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JsonResult.build(201, "查询无数据");
    }

}
