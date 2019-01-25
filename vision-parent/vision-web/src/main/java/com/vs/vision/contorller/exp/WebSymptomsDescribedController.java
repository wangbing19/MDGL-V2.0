package com.vs.vision.contorller.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.exp.ExpertReply;
import com.vs.vision.pojo.exp.SymptomsDescribed;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/sym/")
public class WebSymptomsDescribedController {

	private static final String provider_url = "http://localhost:8024";
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 通过前端获取远程诊断表remoteDiagnoseId并查询出remoteDiagnoseId(症状描述表)对应的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doSelectSym.do")
	@ResponseBody
	public JsonResult doSelectSym(Integer id) {
		//System.out.println("id="+id);
		try {
			SymptomsDescribed selectSym = 
					restTemplate.postForObject(provider_url+"/doSelectSym",id,SymptomsDescribed.class);
			return JsonResult.oK(selectSym);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "当前数据已不存在");
	}
	
	
	/**
	 * 从浏览器获取添加信息在症状描述表进行添加
	 * @param entity
	 * @return
	 */
	@RequestMapping("doInsertSym.do")
	@ResponseBody
	public JsonResult doInsertSym(SymptomsDescribed entity) {
		//SymptomsDescribedService.insertSym(entity);
		try {
			Integer fo = 
					restTemplate.postForObject(provider_url+"/doInsertSym",entity,Integer.class);
			return JsonResult.oK("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加成功");
	}
	
	
	/**
	 * 从浏览器获取要修改的信息并在症状描述表进行修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdateSym.do")
	@ResponseBody
	public JsonResult doUpdateSym(SymptomsDescribed entity) {
		//System.out.println("entity="+entity);
		//SymptomsDescribedService.updateSym(entity);
		try {
			restTemplate.postForObject(provider_url+"/doUpdateSym",entity,Integer.class);
			return JsonResult.oK("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改成功");
	}
}
