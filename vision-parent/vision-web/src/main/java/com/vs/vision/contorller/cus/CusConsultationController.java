package com.vs.vision.contorller.cus;

import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusConsultationVo;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


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
    public JsonResult FindPageObjects(CusConsultationVo cusConsultationVo){
        try {
//        	PageObject<CusConsultation> pageObject = restTemplate.getForObject(provider_url+"/cusConsultation/findPageObjects?name={name}&tel={tel}&pageCurrent={pageCurrent}", PageObject.class,cusConsultationVo);
        	PageObject<CusConsultation> pageObject = restTemplate.postForObject(provider_url+"/cusConsultation/findPageObjects", cusConsultationVo, PageObject.class);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JsonResult.build(201, "查询无数据");
    }

}
