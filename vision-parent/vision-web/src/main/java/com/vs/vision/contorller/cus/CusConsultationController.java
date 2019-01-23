package com.vs.vision.contorller.cus;

import com.vs.vision.vo.JsonResult;
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

    private RestTemplate restTemplate;

    @RequestMapping("doCusConsultationListUI")
    public String doCusConsultationListUI(){
        return "pages/sys/cusConsultation_list";
    }

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult FindPageObjects(String name, String tel, Integer pageCurrent){
     //   return restTemplate.getForObject(provider_url+"/cusConsultation/doFindPageObjects?pageCurrent="+pageCurrent+"&name="+name+"&tel="+tel,JsonResult.class);
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("tel",tel);
        String pageCurrentString = pageCurrent.toString();
        map.put("pageCurrent",pageCurrentString);
        return  restTemplate.getForObject(provider_url+"/cusConsultation/findPageObjects",JsonResult.class,map);
    }

}
