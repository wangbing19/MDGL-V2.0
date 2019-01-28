package com.vs.vision.contorller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/customer")
public class CusCustomerController {

	private static final String provider_url = "http://176.198.114.246:8022";

	@Autowired
	private RestTemplate restTemplate;

	/**点击跳转用户页面*/
	@RequestMapping("/doCustomerListUI")
	public String doCustomerListUI() {
		return "pages/sys/customer_list";
	}

	/**用户页面查看所有信息*/
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(CusVo cusVo) {
		try {
			//获取登录用户信息
        	Users user = ShiroUtils.getUser();
        	
			//获取登录用户id及上级id
			cusVo.setUserId(user.getId());
			cusVo.setUserParentId(user.getParentId());
			PageObject<CusCustomer> pageObject = restTemplate.postForObject(provider_url+"/customer/findPageObjects", cusVo, PageObject.class);
			if(pageObject.getRecords().size()!=0) {
				return JsonResult.oK(pageObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于客户id查询客户所有信息*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			CusCustomer cusCustomer = restTemplate.postForObject(provider_url+"/customer/findObjectById", id, CusCustomer.class);
			if(cusCustomer != null) {
				return JsonResult.oK(cusCustomer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改查询失败");
	}

	/**基于用户id修改用户状态*/
	@RequestMapping("doUpdateStateById")
	@ResponseBody
	public JsonResult doUpdateStateById(CusVo cusVo) {
		try {
			//获取登录用户信息
        	Users user = ShiroUtils.getUser();
        	
			cusVo.setUser(user.getUsername());
			Integer row = restTemplate.postForObject(provider_url+"/customer/updateStateById", cusVo, Integer.class);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "状态修改失败");
	}
	
	/**根据咨询表id查询客户表信息有无*/
	@RequestMapping("doFindConsultationByConsultationId")
	@ResponseBody
	public JsonResult doFindConsultationIdByConsultationId(Integer consultationId) {
		try {
			Integer row = restTemplate.postForObject(provider_url+"/customer/findConsultationByConsultationId", consultationId, Integer.class);
			if(row == 0 || row == 1) {
				return JsonResult.oK(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "已有数据,无法添加");
	}
	
	/**跳转到新增或修改信息*/
	@RequestMapping("/doCustomerEditUI")
	public String doCustomerEditUI() {
		return "pages/sys/customer_edit";  
	}
	
	
	/**将CusCustomer类型数据添加到数据库*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(CusCustomer cusCustomer) {
		try {
			//获取登录用户信息
        	Users Users = ShiroUtils.getUser();
        	//获取登录用户创建客户数量
			
			  if(Users.getDeptNum()>=Users.getDeptLimit()) { return JsonResult.build(201,
			  "创建客户数量已达上限,无法再次创建客户,请联系总店208-62825475"); }
			  
			 
        	
			cusCustomer.setCreatedUser(Users.getUsername());
			cusCustomer.setModifiedUser(cusCustomer.getCreatedUser());
			cusCustomer.setUserId(Users.getId());
			cusCustomer.setUserParentId(Users.getParentId());
			
			Integer row = restTemplate.postForObject(provider_url+"/customer/saveObject", cusCustomer, Integer.class);
			if(row != 0 && row != null) {
				  //添加登录用户创建客户数量 
				Users.setDeptNum(Users.getDeptNum()+1);
				  restTemplate.postForObject("http://176.198.114.212.:8029/user/doUpdateObject", Users, JsonResult.class);//176.198.114.212
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "此客户可能已存在");
	}
	
	
	/**基于id删除客户信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			Integer row = restTemplate.postForObject(provider_url+"/customer/deleteObject", id, Integer.class);
			if(row != 0 && row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "此客户可能已经不存在");
	}
	
	
	/**基于客户id修改客户信息*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CusCustomer cusCustomer) {
		try {
			//获取登录用户信息
        	Users user = ShiroUtils.getUser();
			
			cusCustomer.setModifiedUser(user.getUsername());
			
			Integer row = restTemplate.postForObject(provider_url+"/customer/updateObject", cusCustomer, Integer.class);
			if(row != 0 && row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "此客户信息修改失败");
	}
	

}
