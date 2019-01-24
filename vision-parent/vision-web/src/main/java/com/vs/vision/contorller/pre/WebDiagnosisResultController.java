package com.vs.vision.contorller.pre;

import java.util.List;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.pre.DiagnosisDesc;
import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.vo.JsonResult;


@Controller
@RequestMapping("/diagnosis")

public class WebDiagnosisResultController {
	public static final String local_url = "http://localhost:8026";
	@Autowired
	private RestTemplate restTemplate;
	
	//点击菜单刷新诊断处方页面
	@RequestMapping("/doDiagnosis.do")
	public String diagnasisResultUI() {
		return "pages/sys/diagnosis_list";
	}
	
	//查询所有数据刷新诊断处方信息表数据
	@RequestMapping("/doFindObjects.do")
	@ResponseBody
	public JsonResult doFindObjects(){
		String url = local_url+"/diagnosisResult/doFindObjects";
		List<DiagnosisResult> listObjects = restTemplate.getForObject(url,List.class);
		return JsonResult.oK(listObjects);
	}
	
	//根据症状id查询症状描述
	@RequestMapping("/doFindDescObject.do")
	@ResponseBody
	public JsonResult doFindDescObject(Integer diagnosisId) {
		System.out.println("诊断处方按钮对应的id:"+diagnosisId);
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("diagnosisId", diagnosisId);
		String url = local_url+"/diagnosisDesc/findDescObjectById";
		DiagnosisDesc descObject= restTemplate.postForObject(url,map,DiagnosisDesc.class);
		return JsonResult.oK(descObject);
	}	
	//根据针状id删除症状
	@RequestMapping("/doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		System.out.println("前台选中需要删除的症状id："+id);
		String url = local_url+"/diagnosisResult/deleteResultObjectById";
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("diagnosisId", id);
		String deleteMessage = restTemplate.postForObject(url,map,String.class);
		return JsonResult.oK(deleteMessage);		
	}	
}



























