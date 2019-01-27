package com.vs.vision.contorller.tra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/tra/")
public class WebTraInformationrecordController {
	private static final String provider_url = "http://176.198.114.246:8030/traInformationrecord";
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
	public JsonResult doFindPageObjects(CusVo cusVo){
		// 获取登录用户的账号
		Users user=ShiroUtils.getUser(); 
		cusVo.setUserId(user.getId());
		cusVo.setUserParentId(user.getParentId());
		try {
			PageObject<TraInformationrecord> postForObject = restTemplate.postForObject(provider_url+"/doFindPageObjects",cusVo,PageObject.class);
			if(postForObject.getRecords().size()!=0) {
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
	public JsonResult doInsertUI(TraInformationrecord entity){
		Users user=ShiroUtils.getUser();
		entity.setUserParentId(user.getParentId());
		entity.setUserId(user.getId());
		try {
			Integer rows = restTemplate.postForObject(provider_url+"/doSaveObject", entity, Integer.class);
			if(rows != null && rows != 0) {
				restTemplate.postForObject("http://176.198.114.246:8022/customer/updateObjectByTimesOfTraining", entity, Integer.class);
				return JsonResult.oK("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "添加数据异常,请稍后重试");
	}
	
	
	/**删除*/
	@RequestMapping("doDeleteObject.do")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer rows = restTemplate.postForObject(provider_url+"/doDeleteObject", id, Integer.class);
			if(rows != null && rows != 0) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "数据可能已不存在");
	}
	
	
	/**通过id查询*/
	@RequestMapping("doSelectUI.do")
	@ResponseBody
	public JsonResult doSelect(Integer id) {
		try {
			TraInformationrecord entity = restTemplate.postForObject(provider_url+"/doSelectUI",id,TraInformationrecord.class);
			if(entity != null) {
				return JsonResult.oK(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}
	
	
	/**通过id修改训练表信息*/
	@RequestMapping("doUpdate.do")
	@ResponseBody
	public JsonResult doUpdate(TraInformationrecord entity) {
		Users user = ShiroUtils.getUser();
		entity.setCreatedUser(user.getUsername());
		entity.setModifiedUser(user.getUsername());
		entity.setUserId(user.getId());
		entity.setUserParentId(user.getParentId());
		try {
			Integer rows = restTemplate.postForObject(provider_url+"/doUpdate", entity, Integer.class);
			if(rows != null && rows != 0) {
				return JsonResult.oK("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改保存数据错误,请稍后重试");
	}
	
	
}
