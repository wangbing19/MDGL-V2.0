package com.vs.vision.contorller.exp;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dia/")
public class WebRemoteDiagnoseController {

	private static final String provider_url = "http://localhost:8024";
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 进入远程诊断的显示页面
	 * @return
	 */
	@RequestMapping("doRemoteDiagnose.do")
	public String doRemoteDiagnose(){
		return "pages/sys/exp_remote_diagnose";
	}

	/**
	 * 远程诊断管理的添加和修改页面
	 * @return
	 */
	@RequestMapping("doUserEditUI.do")
	public String doUserEditUI(){
		return "pages/sys/exp_remote_diagnose_edit";
	}


	/**
	 * 点击症状描述按钮进入症状描述页面
	 * @return
	 */
	@RequestMapping("doSymptoms.do")
	public String dosymptoms() {
		return "pages/sys/exp_symptoms_described";
	}


	/**
	 * 点击专家回复按钮进入专家回复页面
	 * @return
	 */
	@RequestMapping("doExpertReply.do")
	public String doExpertReply() {
		return "pages/sys/exp_expert_reply";
	}


	/**
	 * 对应前端页面远程诊断模块呈现页面,
	 * 从前端获取当前页码值并的开始下标返回指定长度的信息,
	 * 获取输入框中customerName的名字,没有则查询全部
	 * 并在数据库进行分页查询返回查询的信息
	 * @param customerName
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String customerName,Integer pageCurrent){
		Map<Object, Object> map = new HashMap<>();
		map.put("customerName",customerName);
		map.put("pageCurrent",pageCurrent);
		try {
			PageObject<ExpRemoteDiagnoseVo> postForObject = restTemplate.postForObject(provider_url+"/doFindPageObjects",map,PageObject.class);
			if(!(postForObject.getRecords().size()==0)) {
				return JsonResult.oK(postForObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  JsonResult.build(201, "查询无数据");
	}


	/**
	 * 通过选择的id在修改页面获取远程诊断表对应id中的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doSelect.do")
	@ResponseBody
	public JsonResult doSelect(Integer id) {
		try {
			ExpRemoteDiagnoseVo entity = 
					restTemplate.postForObject(provider_url+"/doSelect",id,ExpRemoteDiagnoseVo.class);
			return JsonResult.oK(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}


	/**
	 * 显示是否解决的点击事件切换
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("doValidById.do")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid){
		Map<String, Integer> map = new HashMap<>();
		map.put("id",id);
		map.put("valid",valid);
		try {
			Integer i = restTemplate.postForObject(provider_url+"/doValidById", map, Integer.class);
			if(!StringUtils.isEmpty(i)) {
				return JsonResult.oK("切换成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "切换失败");
	}
	
	
	/**
	 * 删除:从前端获取id,通过id从数据库删除对应的数据
	 * @param id
	 * @return
	 */
	@RequestMapping("doDelete.do")
	@ResponseBody
	public JsonResult doDelete(Integer... ids) {
		//System.out.println("id234="+ids);
		//expDiagnoseService.delete(ids);
		try {
			Integer is = restTemplate.postForObject(provider_url+"/doDelete", ids, Integer.class);
			if(!StringUtils.isEmpty(is)) {
				return JsonResult.oK("删除成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "数据已不存在");
	}
	
	
	/**对应前端用户管理模块中的添加作用
	 *将从前端获取的用户信息和角色信息添加到数据库
	 */
	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(
			RemoteDiagnose entity){
		//System.out.println("ExpRemoteDiagnose="+entity);
		//expDiagnoseService.saveObject(entity);
		try {
			Integer en = restTemplate.postForObject(provider_url+"/doSaveObject", entity, Integer.class);
			if(!StringUtils.isEmpty(en)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "添加失败");
	}
	
	
	/**
	 * 获取修改后的数据传入数据库
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdate.do")
	@ResponseBody
	public JsonResult doUpdate(RemoteDiagnose entity) {
		//expDiagnoseService.update(entity);
		try {
			Integer ps = restTemplate.postForObject(provider_url+"/doUpdate", entity, Integer.class);
			if(!StringUtils.isEmpty(ps)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败");
	}
}
