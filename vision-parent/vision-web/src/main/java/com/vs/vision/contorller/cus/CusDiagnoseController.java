package com.vs.vision.contorller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.CusDiagnose;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/cusDiagnose")
public class CusDiagnoseController {
	
	private static final String provider_url = "http://localhost:8022";

	@Autowired
	private RestTemplate restTemplate;
	
	/**点击跳转用户页面*/
	@RequestMapping("/doCusDiagnoseListUI")
	public String doCusConsultationListUI() {
		return "pages/sys/cusDiagnose_list";
	}
	
	/**诊断表页面加载,查询*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(CusVo cusVo) {
		try {
			cusVo.setUserId(0);
			cusVo.setUserParentId(0);
			PageObject<CusDiagnose> pageObject = restTemplate.postForObject(provider_url+"/cusDiagnose/findPageObjects", cusVo, PageObject.class);
			if(pageObject.getRecords().size()!=0) {
				return JsonResult.oK(pageObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于诊断表id,查询相关id所有信息*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			CusDiagnose cusDiagnose = restTemplate.postForObject(provider_url+"/cusDiagnose/findObjectById", id, CusDiagnose.class);
			if(cusDiagnose != null) {
				return JsonResult.oK(cusDiagnose);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**诊断表修改页面跳转*/
	@RequestMapping("/doCusDiagnoseEditUI")
	public String doCusDiagnoseEditUI() {
		return "pages/sys/cusDiagnose_edit";
	}
	
	/**基于客户id查询诊断表相关信息*/
	@RequestMapping("/doFindObjectByCustomerId")
	@ResponseBody
	public JsonResult doFindObjectByCustomerId(Integer customerId) {
		try {
			CusDiagnose cusDiagnose = restTemplate.postForObject(provider_url+"/cusDiagnose/findByCustomerId", customerId, CusDiagnose.class);
			if(cusDiagnose != null) {
				return JsonResult.oK(cusDiagnose);
			} else if(cusDiagnose == null){
				return JsonResult.build(203, "无数据,需新增数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**基于客户id创建客户诊断表*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(CusDiagnose cusDiagnose) {
		try {
			cusDiagnose.setCreatedUser("admin");
			cusDiagnose.setModifiedUser(cusDiagnose.getCreatedUser());
			cusDiagnose.setUserId(0);
			cusDiagnose.setUserParentId(0);
			Integer row = restTemplate.postForObject(provider_url+"/cusDiagnose/saveObject", cusDiagnose, Integer.class);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "新增诊断表错误");
	}
	
	/**基于诊断表id删除数据*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer row = restTemplate.postForObject(provider_url+"/cusDiagnose/deleteObject", id, Integer.class);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "数据可能已删除,请刷新");
	}
	
}
