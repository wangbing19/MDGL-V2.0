package com.vs.vision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.pojo.exp.ExpertReply;
import com.vs.vision.service.ExpertReplyService;

@Controller
public class ExpertReplyController {
	
	@Autowired
	private ExpertReplyService expertReplyService;
	
	/**通过id查询专家回复的数据*/
	@RequestMapping("doSelectRep")
	@ResponseBody
	public ExpertReply selectRep(@RequestBody Integer id) {
		return expertReplyService.selectRep(id);
	}
	
	
	/**专家回复新增*/
	@RequestMapping("doInsertRep")
	@ResponseBody
	public Integer doInsertRep(@RequestBody ExpertReply entity) {
		return expertReplyService.doInsertRep(entity);
	}
	
	
	/**删除*/
	/*@RequestMapping("doDeleteRep")
	@ResponseBody
	public Integer doDeleteRep(@RequestBody Integer... id) {
		return expertReplyService.doDeleteRep(id);
	}*/
	
	
	/**专家回复修改*/
	@RequestMapping("doUpdateRep")
	@ResponseBody
	public Integer doUpdateRep(@RequestBody ExpertReply entity) {
		return expertReplyService.doUpdateRep(entity);
	}
}
