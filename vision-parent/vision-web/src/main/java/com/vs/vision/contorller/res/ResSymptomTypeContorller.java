package com.vs.vision.contorller.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	private static final String provider_url = "http://localhost:8028";

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
	public JsonResult dofindObjects(Integer page) {
		try {
			System.out.println(page);
			int userId=0;
			List<ResSymptomType> result =	restTemplate.postForObject(provider_url+"/symptomType/findAll", userId, List.class);
			return JsonResult.oK(result);
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
	
	@RequestMapping("/doResSymptomTypeEdit")
	public String  doResSymptomTypeEdit(ResSymptomType pesSymptomType) {
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


