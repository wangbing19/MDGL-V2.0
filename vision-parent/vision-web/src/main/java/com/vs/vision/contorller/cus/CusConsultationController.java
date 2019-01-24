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
        	//获取登录用户id及上级id
        	cusConsultationVo.setUserId(0);
        	cusConsultationVo.setUserParentId(0);
        	
        	PageObject<CusConsultation> pageObject = restTemplate.postForObject(provider_url+"/cusConsultation/findPageObjects", cusConsultationVo, PageObject.class);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JsonResult.build(201, "查询无数据");
    }
    
    /**跳转到修改或新增信息*/
	@RequestMapping("doCusConsultationEditUI")
	public String doCusConsultationEditUI() {
		return "pages/sys/cusConsultation_edit";
	}
    
	
	/**将CusCustomer类型数据添加到数据库*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(CusConsultation cusConsultation) {
		try {
			cusConsultation.setUserId(0);
			cusConsultation.setUserParentId(0);
			Integer row = restTemplate.postForObject(provider_url+"/cusConsultation/saveObject", cusConsultation, Integer.class);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存失败");
	}
	
	/**基于id删除咨询表信息*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer row = restTemplate.postForObject(provider_url+"/cusConsultation/deleteObject", id, Integer.class);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除失败,数据或已删除");
	}
	
	/**基于咨询表id,查询相关id所有信息*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			CusConsultation cusConsultation = restTemplate.postForObject(provider_url+"/cusConsultation/findObjectById", id, CusConsultation.class);
			if(cusConsultation != null) {
				return JsonResult.oK(cusConsultation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询失败");
	}
	
	/**基于咨询表id更改用户信息*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CusConsultation cusConsultation) {
		try {
			cusConsultation.setUserId(0);
			cusConsultation.setUserParentId(0);
			Integer row = restTemplate.postForObject(provider_url+"/cusConsultation/updateObject", cusConsultation, Integer.class);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改保存失败");
	}
	
	
}
