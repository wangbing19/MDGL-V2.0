package com.vs.vision.contorller.rec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.vo.JsonResult;

@Controller
@RequestMapping("/recUser")
public class WebRecUser {
	@RequestMapping("/doRecUserRecordUI.do")
	public String doRecUserRecord() {
		return "pages/sys/rec_user_record";
	}
	
	@RequestMapping("/doFindPageObjectsByUserId")
	@ResponseBody
	public JsonResult doFindPageObjects() {
		System.out.println("前台请求根据门店id，父级门店id查询数据");
		return JsonResult.oK();
	}
}
