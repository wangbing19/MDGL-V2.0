package com.vs.vision.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.service.TraInformationrecordService;
import com.vs.vision.vo.PageObject;

@Controller
@RequestMapping("/traInformationrecord")
public class TraInformationrecordController {
	
	@Autowired
	private TraInformationrecordService traInformationrecordService;
	
	/**训练记录分页及姓名查询*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public PageObject<TraInformationrecord> doFindPageObjects(@RequestBody CusVo cusVo){
		try {
			return traInformationrecordService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("训练记录分页及姓名查询==============错误=======================");
		}
		return null;
	}
	
	
	/**添加训练记录到数据库*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public Integer doSaveObject(@RequestBody TraInformationrecord entity) {
		try {
			return traInformationrecordService.doSaveObject(entity);
		} catch (Exception e) {
			System.out.println("添加训练记录到数据库==============错误=======================");
		}
		return null;
	}
	
	
	/**删除*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public Integer doDeleteObject(@RequestBody Integer id) {
		try {
			return traInformationrecordService.doDeleteObject(id);
		} catch (Exception e) {
			System.out.println("从数据删除训练记录表信息==============错误=======================");
		}
		return null;
	}
	
	
	/**通过id查询*/
	@RequestMapping("doSelectUI")
	@ResponseBody
	public TraInformationrecord doSelect(@RequestBody Integer id) {
		try {
			return traInformationrecordService.doSelect(id);
		} catch (Exception e) {
			System.out.println("通过id查询训练表信息==============错误=======================");
		}
		return null;
	}
	
	
	/**通过id修改训练表信息*/
	@RequestMapping("doUpdate")
	@ResponseBody
	public Integer doUpdate(@RequestBody TraInformationrecord entity) {
		try {
			return traInformationrecordService.doUpdate(entity);
		} catch (Exception e) {
			System.out.println("通过id修改训练表信息==============错误=======================");
		}
		return null;
	}
}
