package com.vs.cus.controller;

import com.vs.cus.service.CusConsultationService;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    @Autowired
    private CusConsultationService cusConsultationService;

    
    /**基于用户/电话及当前页码值条件查询用户信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public PageObject<CusConsultation> findPageObjects(@RequestBody CusVo cusVo){
    	try {
    		 return cusConsultationService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("基于用户/电话及当前页码值条件查询用户信息=============错误=================");
		}
    	
        return null;
    }
    
    /**将CusCustomer类型数据添加到数据库*/
    @RequestMapping("/saveObject")
    @ResponseBody
    public Integer saveObject(@RequestBody CusConsultation cusConsultation) {
    	try {
    		return cusConsultationService.saveObject(cusConsultation);
		} catch (Exception e) {
			System.out.println("将CusCustomer类型数据添加到数据库=============错误==================");
		}
    	return null;
    }
    
	/**基于id删除咨询表信息*/
	@RequestMapping("deleteObject")
	@ResponseBody
	public Integer deleteObject(@RequestBody Integer id) {
		try {
			return cusConsultationService.deleteObject(id);
		} catch (Exception e) {
			System.out.println("基于id删除咨询表信息=============错误==================");
		}
		return null;
	}
    
	/**基于咨询表id,查询相关id所有信息*/
	@RequestMapping("findObjectById")
	@ResponseBody
	public CusConsultation findObjectById(@RequestBody Integer id) {
		try {
			CusConsultation cusConsultation = cusConsultationService.findObjectById(id);
			if(cusConsultation != null) {
				return cusConsultation;
			}
		} catch (Exception e) {
			System.out.println("基于咨询表id,查询相关id所有信息=============错误==================");
		}
		return null;
	}
	

	/**基于咨询表id更改用户信息*/
	@RequestMapping("updateObject")
	@ResponseBody
	public Integer doUpdateObject(@RequestBody CusConsultation cusConsultation) {
		try {
			return cusConsultationService.updateObject(cusConsultation);
		} catch (Exception e) {
			System.out.println("基于咨询表id更改用户信息=============错误==================");
		}
		return null;
	}

}
