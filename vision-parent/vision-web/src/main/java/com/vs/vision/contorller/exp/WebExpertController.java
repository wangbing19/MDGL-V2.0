package com.vs.vision.contorller.exp;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.Expert;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/exp/")
public class WebExpertController {

	private static final String provider_url = "http://localhost:8024";
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 进入专家列表添加和修改页面
	 * @return
	 */
	//@RequiresPermissions("exp:zj:add")
	@RequestMapping("doExpertIU.do")
	public String doExpertIU() {
		return "pages/sys/exp_expert_edit";
	}

	/**
	 * 进入专家列表页面
	 * @return
	 */
	@RequestMapping("doExpert.do")
	public String doExpert() {
		return "pages/sys/exp_expert";
	}

	/**
	 * 进入专家信息页面
	 * @return
	 */
	@RequestMapping("doExpertMessage.do")
	public String doExpertMessage() {
		return "pages/sys/exp_expert_message";
	}


	/**
	 * 对应远程诊断修改页面中的点击选择专家弹出的专家姓名
	 * @return
	 */
	@RequestMapping("doFindZTreeNodes.do")
	@ResponseBody
	public JsonResult doFindZTreeNodes(){
		//System.out.println("aaa="+expertService.selectExpName());
		Node[] node = restTemplate.getForObject(provider_url+"/doFindZTreeNodes", Node[].class);
		ArrayList<Node> list = new ArrayList<>();
		for(int i=0;i<node.length;i++) {
			list.add(node[i]);
		}
		try {
			if(list.size()!=0) {
				return JsonResult.oK(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "没有数据");
	}


	/**
	 * 基于条件进行分页查询
	 * @param expertName
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("doLimitExp.do")
	@ResponseBody
	public JsonResult doLimitExp(String expertName,Integer pageCurrent) {
		//System.out.println("expertName="+expertName);
		Map<Object, Object> map = new HashMap<>();
		map.put("expertName",expertName);
		map.put("pageCurrent",pageCurrent);
		try {

			PageObject<Expert> fo = restTemplate.postForObject(provider_url+"/doLimitExp",map,PageObject.class);
			if(!(fo.getRecords().size()==0)) {
				return JsonResult.oK(fo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"查询无数据");
	}


	/**
	 * 通过选择的id在修改页面获取专家表对应id中的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doSelectExp.do")
	@ResponseBody
	public JsonResult doSelectExp(Integer id) {
		//Expert entity = expertService.selectExp(id);
		try {
			Expert entity = restTemplate.postForObject(provider_url+"/doSelectExp",id,Expert.class);
			return JsonResult.oK(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}


	/**
	 * 从浏览器获取修改信息(不包含专家信息这一列)并在数据库进行修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdateExp.do")
	@ResponseBody
	public JsonResult doUpdateExp(Expert entity) {
		//expertService.updateExp(entity);
		try {
			Integer pf = restTemplate.postForObject(provider_url+"/doUpdateExp",entity,Integer.class);
			if(!StringUtils.isEmpty(pf)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
	}


	/**
	 * 从浏览器获取要删除的id并在专家表进行删除
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteExp.do")
	@ResponseBody
	public JsonResult doDeleteExp(Integer... ids) {
		//expertService.deleteExp(ids);
		try {
			Integer po = restTemplate.postForObject(provider_url+"/doDeleteExp",ids,Integer.class);
			if(!StringUtils.isEmpty(po)) {
				return JsonResult.oK("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"删除失败");
	}


	/**
	 * 从浏览器获取添加信息(不包含专家信息这一列)并在专家表进行添加
	 * @param entity
	 * @return
	 */
	@RequestMapping("doInsertExp.do")
	@ResponseBody
	public JsonResult doInsertExp(Expert entity) {
		//System.out.println("专家表添加="+entity);
		//expertService.insertExp(entity);
		try {
			Integer pf = restTemplate.postForObject(provider_url+"/doInsertExp",entity,Integer.class);
			if(!StringUtils.isEmpty(pf)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加失败");
	}


	/**添加或修改专家信息*/
	@RequestMapping("doUpdateMessage.do")
	@ResponseBody
	public JsonResult doUpdateMessage(Expert entity) {
		//expertService.updateMessage(entity);
		try {
			Integer pf = restTemplate.postForObject(provider_url+"/doUpdateMessage",entity,Integer.class);
			if(!StringUtils.isEmpty(pf)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
	}

}
