package com.vs.res.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vs.res.servise.ResProjectConfigService;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.PageObject;
@RestController
public class ResProjectConfigContorller {
	@Autowired
	private ResProjectConfigService resProjectConfigService;
	
	
	/**查询所有配置类型*/
	@RequestMapping("findAll")
	public PageObject<ResProjectConfig> dofindObjects(@RequestBody Map map){
		try {
			PageObject<ResProjectConfig> result=resProjectConfigService.dofindObjects(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**添加*/
	@RequestMapping("doSaveObject")
	public Integer doSaveObject(@RequestBody ResProjectConfig entity) {
		return resProjectConfigService.doSaveObject(entity);
	}
	
	
	/**修改*/
	@RequestMapping("doUpdate")
	public Integer doUpdate(@RequestBody ResProjectConfig entity) {
		return resProjectConfigService.doUpdate(entity);
	}
	
	
	/**删除*/
	@RequestMapping("doDelete")
	public Integer doDelete(@RequestBody Integer... ids) {
		return resProjectConfigService.doDelete(ids);
	}
	
	
	/**修改状态*/
	@RequestMapping("doprojectStateById")
	public Integer doprojectStateById(@RequestBody Map map) {
		
		return resProjectConfigService.doprojectStateById(map);
	}
}
