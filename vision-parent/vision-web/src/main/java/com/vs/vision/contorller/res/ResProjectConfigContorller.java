package com.vs.vision.contorller.res;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/ResProjectConfig/")
public class ResProjectConfigContorller {

	private static final String provider_url = "http://176.198.114.246:8028";

	@Autowired
	private RestTemplate restTemplate;



	@RequestMapping("doResProjectConfigUI")
	public String doResProjectConfigUI() {
		return "/pages/sys/ResProjectConfig_List";
	}
	
	
	@RequestMapping("doPagingUI.do")
	public String doPagingUI() {
		return "/pages/sys/ResProjectConfig_edit";
	}

	/**
	 * 查询所有症状类型信息
	 * @return
	 */
	@RequestMapping("/doFingPageObject")
	@ResponseBody
	public JsonResult dofindObjects(String projectName,Integer pageCurrent) {
		Users user = ShiroUtils.getUser();
		Integer userId = user.getId();
		try {
			Map<String,Object> map= new HashMap<>();
			map.put("projectName", projectName);
			map.put("pageCurrent", pageCurrent);
			map.put("userId", userId);
			PageObject<ResProjectConfig> postForObject = restTemplate.postForObject(provider_url + "/findAll", map, PageObject.class);
			List<ResProjectConfig> records = postForObject.getRecords();
			return JsonResult.oK(postForObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询失败！");
	}
	
	
	@RequestMapping("dosaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(
			ResProjectConfig entity){
		Users user=ShiroUtils.getUser();
		Integer userId = user.getId();
		entity.setUserId(userId);
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
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
	
	
	@RequestMapping("doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(ResProjectConfig entity) {
		//expDiagnoseService.update(entity);
		Users user = ShiroUtils.getUser();
		entity.setGmtModified(new Date());
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
	
	
	
	@RequestMapping("doDelete.do")
	@ResponseBody
	public JsonResult doDelete(Integer... ids) {
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
	
	
	@RequestMapping("doprojectStateById.do")
	@ResponseBody
	public JsonResult doprojectStateById(Integer id,Integer projectState){
		Map<Object, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("projectState",projectState);
		try {
			Integer i = restTemplate.postForObject(provider_url+"/doprojectStateById", map, Integer.class);
			if(!StringUtils.isEmpty(i)) {
				return JsonResult.oK("切换成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "切换失败");
	}
}
