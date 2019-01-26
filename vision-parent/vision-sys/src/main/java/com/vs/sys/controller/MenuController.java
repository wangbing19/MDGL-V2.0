package com.vs.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.sys.service.MenuService;
import com.vs.vision.pojo.sys.Menus;
import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(@RequestBody Menus entity){
    	menuService.updateObject(entity);
    	return JsonResult.oK();
    }
    
    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(@RequestBody Menus entity){
    	menuService.saveObject(entity);
    	return JsonResult.oK();
    }
    
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes(){
		 return new JsonResult(
		 menuService.findZtreeMenuNodes());
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(@RequestBody Integer id){
		menuService.deleteObject(id);
		return JsonResult.oK();
	}

	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Map<String,Object>> list=
				menuService.findObjects();
		return new JsonResult(list);
	}

}
