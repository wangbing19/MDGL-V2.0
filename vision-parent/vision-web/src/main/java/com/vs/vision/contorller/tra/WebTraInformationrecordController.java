package com.vs.vision.contorller.tra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/tra/")
public class WebTraInformationrecordController {
	private static final String provider_url = "http://localhost:8030";
	@Autowired
	private RestTemplate restTemplate;
	
	/**训练管理页面*/
	@RequestMapping("doUserEditUI.do")
	public String doUserEditUI() {
		return "pages/sys/TraInformationrecord_edit";
	}
	
	
	/**添加和修改页面*/
	@RequestMapping("doTraInformationrecordUI.do")
	public String doTraInformationrecordUI() {
		return "pages/sys/TraInformationrecord_list";
	}
	
	/**分页*/
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,Integer pageCurrent){
		// 获取登录用户的账号
		Users user=ShiroUtils.getUser(); 
		Integer userParentId = user.getParentId();
		Map<Object, Object> map = new HashMap<>();
		map.put("name",name);
		map.put("pageCurrent",pageCurrent);
		map.put("userParentId", userParentId);
		try {
			PageObject<TraInformationrecord> postForObject = restTemplate.postForObject(provider_url+"/doFindPageObjects",map,PageObject.class);
			if(!(postForObject.getRecords().size()==0)) {
				return JsonResult.oK(postForObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  JsonResult.build(201, "查询无数据");
	}
	
	
	/**添加*/
	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doInsertUI(
			TraInformationrecord entity){
		Users user=ShiroUtils.getUser();
		Integer userParentId=user.getParentId();
		Integer userId = user.getId();
		entity.setUserParentId(userParentId);
		entity.setUserId(userId);
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
	
	
	/**删除*/
	@RequestMapping("doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer is = restTemplate.postForObject(provider_url+"/doDeleteObject", id, Integer.class);
			if(!StringUtils.isEmpty(is)) {
				return JsonResult.oK("删除成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "数据已不存在");
	}
	
	
	/**通过id查询*/
	@RequestMapping("doSelectUI.do")
	@ResponseBody
	public JsonResult doSelect(Integer id) {
		try {
			TraInformationrecord entity = 
					restTemplate.postForObject(provider_url+"/doSelectUI",id,TraInformationrecord.class);
			return JsonResult.oK(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}
	
	
	/**修改*/
	@RequestMapping("doUpdate.do")
	@ResponseBody
	public JsonResult doUpdate(TraInformationrecord entity) {
		//expDiagnoseService.update(entity);
		Users user = ShiroUtils.getUser();
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
