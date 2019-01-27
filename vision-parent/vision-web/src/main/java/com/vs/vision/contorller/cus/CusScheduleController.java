package com.vs.vision.contorller.cus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/cusSchedule")
public class CusScheduleController {
	
	private static final String provider_url = "http://localhost:8022";

	@Autowired
	private RestTemplate restTemplate;
	
	/**转向课程页面*/
	@RequestMapping("/doScheduleListUI")
	public String doScheduleListUI() {
		return "/pages/sys/cusSchedule_list";
	}
	
	/**转向课程修改页面*/
	@RequestMapping("/doscheduleEditUI")
	public String doscheduleEditUI() {
		return "/pages/sys/cusSchedule_edit";
	}
	
	/**基于用户/电话及当前页码值条件查询课程信息*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(CusVo cusVo) {
		try {
			//获取登录用户信息
        	Users user = ShiroUtils.getUser();
			
			//获取登录用户id及上级id
        	cusVo.setUserId(user.getId());
        	cusVo.setUserParentId(user.getParentId());
        	PageObject<CusSchedule> pageObject = restTemplate.postForObject(provider_url+"/schedule/findPageObjects", cusVo, PageObject.class);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于id删除课程信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer rows = restTemplate.postForObject(provider_url+"/schedule/deleteObject", id, Integer.class);
			if(rows !=0 && rows !=null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该数据可能已经被删除");
	}
	
	/**基于id查询课程信息*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			CusSchedule cusSchedule = restTemplate.postForObject(provider_url+"/schedule/findObjectById", id, CusSchedule.class);
			if(cusSchedule != null) {
				return JsonResult.oK(cusSchedule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**创建客户课程表*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(CusSchedule cusSchedule) {
		try {
			//获取登录用户信息
        	Users user = ShiroUtils.getUser();
			cusSchedule.setUserId(user.getId());
			cusSchedule.setUserParentId(user.getParentId());
			cusSchedule.setCreatedUser(user.getUsername());
			cusSchedule.setModifiedUser(user.getUsername());
			Integer rows = restTemplate.postForObject(provider_url+"/schedule/saveObject", cusSchedule, Integer.class);
			if(rows != 0 && rows != null) {
				
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存数据错误,请稍后重试");
	}
	
	
	/**修改课程表数据*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CusSchedule cusSchedule) {
		try {
			cusSchedule.setModifiedUser("admin");
			Integer rows = restTemplate.postForObject(provider_url+"/schedule/updateObject", cusSchedule, Integer.class);
			if(rows != 0 && rows != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改数据错误,请稍后重试");
	}
	
	/**基于客户id查询用户课程表信息*/
	@RequestMapping("/doFindByCustomerId")
	@ResponseBody
	public JsonResult doFindByCustomerId(Integer customerId) {
		try {
			List<CusSchedule> list = restTemplate.postForObject(provider_url+"/schedule/findByCustomerId", customerId, List.class);
			if(list.size()!=0 && list != null) {
				return JsonResult.oK(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该用户无课程,需添加课程信息");
	}
	
	
	
	
}
