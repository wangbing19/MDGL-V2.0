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
import com.vs.vision.vo.DiagnosisDate;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.Node;


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
	
	//跳转到新增或者更新页面新增症状
	@RequestMapping("/doDiagnosisInsertOrUpdate.do")
	public String doDiagnosisInsertOrUpdate() {
		return "pages/sys/diagnosis_edit";
	}
	
	//请求后台加载ztree节点资源
	@RequestMapping("/doFindZtreeMenuNodes.do")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		System.out.println("请求后台加载ztree资源");
		String url = local_url + "/diagnosisResult/findZtreeMenuNodes";
		List<Node> ztreeDate = restTemplate.getForObject(url, List.class);
		return JsonResult.oK(ztreeDate);
	}
	
	//根据症状id查询父级症状的名字
	@RequestMapping("/doFindParentNameById.do")
	@ResponseBody
	public JsonResult doFindParentNameById(Integer id) {
		System.out.println("请求后台查询父级症状的名称");
		String url = local_url+"/diagnosisResult/findParentNameById";
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("id", id);
		String parentName = restTemplate.postForObject(url,map,String.class);
		return JsonResult.oK(parentName);
	}
	//更新症状信息
	@RequestMapping("/doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(DiagnosisDate diagnosisDate) {
		System.out.println("前台收到保存症状vo信息："+diagnosisDate);
		String url = local_url + "/diagnosisResult/updateObject";
		String updateMessage = restTemplate.postForObject(url, diagnosisDate,String.class);
		return JsonResult.oK(updateMessage);
	}
	//根据id查询一行数据，用于判断显示类型
	@RequestMapping("/doFindObject.do")
	@ResponseBody
	public JsonResult doFindObject(Integer id) {
		System.out.println("前台请求查询id为："+id+"的全部数据");
		String url = local_url+"/diagnosisResult/findObjectById";
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("id", id);
		DiagnosisResult findObject = restTemplate.postForObject(url,map,DiagnosisResult.class);
		return JsonResult.oK(findObject);
	}
	//根据id修改显示类型，（添加症状时使用）
	@RequestMapping("/doUpdateDisType.do")
	@ResponseBody
	public JsonResult doUpdateDisType(Integer disType,Integer id) {
		System.out.println("前台请求修改id为："+id+"的显示状态========"+disType);
		MultiValueMap map = new LinkedMultiValueMap<>();
		map.add("id", id);
		map.add("disType",disType);
		String url = local_url+"/diagnosisResult/updateDisTypeById";
		String updateDisTypeMessage = restTemplate.postForObject(url,map,String.class);
		return JsonResult.oK(updateDisTypeMessage);
	}
	//新增症状信息
	@RequestMapping("/doInsertObject.do")
	@ResponseBody
	public JsonResult doInsertObject(DiagnosisResult diagnosisResult) {
		System.out.println("前台请求后台新增数据");
		String url = local_url+"/diagnosisResult/insertObject";
		String insertMessage = restTemplate.postForObject(url, diagnosisResult,String.class);
		return JsonResult.oK(insertMessage);
	}
}



























