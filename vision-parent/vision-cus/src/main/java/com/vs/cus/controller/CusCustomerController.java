package com.vs.cus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.cus.service.CusCustomerService;
import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/customer")
public class CusCustomerController {
	
	@Autowired
    private CusCustomerService cusCustomerService;
	
	/**用户页面查看所有信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public PageObject<CusCustomer> findPageObjects(@RequestBody CusVo cusVo){
    	try {
    		 return cusCustomerService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("用户页面查看所有信息=============错误=================");
		}
        return null;
    }
    
    /**基于客户id查询客户所有信息*/
    @RequestMapping("/findObjectById")
    @ResponseBody
    public CusCustomer findObjectById(@RequestBody Integer id){
    	try {
    		return cusCustomerService.findObjectById(id);
    	} catch (Exception e) {
    		System.out.println("基于客户id查询客户所有信息=============错误=================");
    	}
    	return null;
    }
    
	/**基于用户id修改用户状态*/
	@RequestMapping("updateStateById")
	@ResponseBody
	public Integer doUpdateStateById(@RequestBody CusVo cusVo) {
		try {
			return cusCustomerService.updateStateById(cusVo);
		} catch (Exception e) {
			System.out.println("基于用户id修改用户状态=============错误=================");
		}
		return null;
	}
	
	/**根据咨询表id查询客户表信息有无*/
	@RequestMapping("findConsultationByConsultationId")
	@ResponseBody
	public Integer findConsultationByConsultationId(@RequestBody Integer consultationId) {
		try {
			return cusCustomerService.findConsultationByConsultationId(consultationId);
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return null;
	}
	
	
}
