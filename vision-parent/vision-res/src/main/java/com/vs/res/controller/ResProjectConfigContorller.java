package com.vs.res.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vs.res.servise.ResProjectConfigService;
import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.PageObject;
@RestController
@RequestMapping("/ResProjectConfig")
public class ResProjectConfigContorller {
	@Autowired
	private ResProjectConfigService resProjectConfigService;
	
	
	/**查询所有配置类型*/
	@RequestMapping("/findAll")
	public PageObject<ResProjectConfig> dofindObjects(@RequestBody Map map){
		try {
			PageObject<ResProjectConfig> result=resProjectConfigService.dofindObjects(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
