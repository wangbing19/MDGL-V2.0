package com.vs.vision.contorller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/customer")
public class CusCustomerController {

	private static final String provider_url = "http://localhost:8022";

	@Autowired
	private RestTemplate restTemplate;

	/**点击跳转用户页面*/
	@RequestMapping("/doCustomerListUI")
	public String doCustomerListUI() {
		return "pages/sys/customer_list";
	}

	/**用户页面查看所有信息*/
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(CusVo cusVo) {
		try {
			//获取登录用户id及上级id
			cusVo.setUserId(0);
			cusVo.setUserParentId(0);
			PageObject<CusCustomer> pageObject = restTemplate.postForObject(provider_url+"/customer/findPageObjects", cusVo, PageObject.class);
			if(!(pageObject.getRecords().size()==0)) {
				return JsonResult.oK(pageObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于客户id查询客户所有信息*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			CusCustomer cusCustomer = restTemplate.postForObject(provider_url+"/customer/findObjectById", id, CusCustomer.class);
			if(cusCustomer != null) {
				return JsonResult.oK(cusCustomer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询失败");
	}


}
