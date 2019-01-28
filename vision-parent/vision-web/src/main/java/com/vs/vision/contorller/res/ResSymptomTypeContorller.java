package com.vs.vision.contorller.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.res.ResSymptomType;
import com.vs.vision.vo.JsonResult;



@Controller
@RequestMapping("/resSymptomType")
public class ResSymptomTypeContorller {


	private static final String provider_url = "http://176.198.114.246:8028";

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 跳转资源配置页面
	 * @author tarena
	 *
	 */	

	@RequestMapping("/doResSymptomTypeList")
	public String doResSymptomTypeList() {
		return "/pages/sys/res_symptom_type_list";
	}



	/**
	 * 查询资源配置所有信息
	 * @return
	 */
	@RequestMapping("/dofindObjects")
	@ResponseBody
	public JsonResult dofindObjects() {
		try {
			int userId=0;
			List<ResSymptomType> result =	restTemplate.postForObject(provider_url+"/symptomType/findAll", userId, List.class);
			if(result.size()!=0) {
				return JsonResult.oK(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询失败");
	}
	
	/**课程表查询咨询表所有信息*/
	@RequestMapping("/doFindSymptomType")
	@ResponseBody
	public JsonResult doFindSymptomType(){
		try {
			Integer userId = 0;
			List<ResSymptomType> list = restTemplate.postForObject(provider_url+"/symptomType/findSymptomType", userId, List.class);
			if(list.size()!=0) {
				return JsonResult.oK(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询数据错误");
	}
	/**
	 * 展示资源配置修改页面
	 * @return
	 */
	@RequestMapping("/doResSymptomTypeEdit")
	public String  doResSymptomTypeEdit() {
		
		return "/pages/sys/res_symptom_type_edit";
	}
	
	/**
	 * 根据id查询资源配置信息
	 * 
	 */
	
	@RequestMapping("dofindPageObject")
	@ResponseBody
	public JsonResult dofindPageObjectOne(ResSymptomType resSymptomType) {
		
		try {
			
			ResSymptomType result = restTemplate.postForObject(provider_url+"/symptomType/dofindPageObject", resSymptomType, ResSymptomType.class);
			System.out.println("前端"+result);
			if(result !=null ) {
				return JsonResult.oK(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonResult.build(201, "修改失败");
	}
	
	/**
	 * 修改资源配置信息
	 */
	@RequestMapping("doupdateObject")
	@ResponseBody
	public JsonResult doupdateObject(ResSymptomType resSymptomType) {
		Integer userId = 0;
		resSymptomType.setUserId(userId);
		Integer result = restTemplate.postForObject(provider_url+"/symptomType/doupdateObject", resSymptomType, Integer.class);
		if(result !=null || result !=0) {
			
			return JsonResult.oK();
		}
		
		return JsonResult.build(201, "修改失败");
	}
	/**
	 * 新增资源配置信息
	 * @return
	 */
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ResSymptomType resSymptomType) {
		try {
			Integer userId = 0;
			resSymptomType.setUserId(userId);
			Integer result = restTemplate.postForObject(provider_url+"/symptomType/doSaveObject", resSymptomType, Integer.class);
			if(result !=null) {
				return JsonResult.oK();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonResult.build(201, "新增失败");
	}
	
	/**
	 * 根据id删除资源配置信息
	 * @param resSymptomType
	 * @return
	 */
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(ResSymptomType resSymptomType) {
		try {
			Integer userId = 0;
			resSymptomType.setUserId(userId);
			Integer result = restTemplate.postForObject(provider_url+"/symptomType/doDeleteObject", resSymptomType, Integer.class);
			if(result !=null) {
				return JsonResult.oK();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonResult.build(201, "删除失败");
	}
}


