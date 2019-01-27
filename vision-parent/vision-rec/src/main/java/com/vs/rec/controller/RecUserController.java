package com.vs.rec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vs.rec.service.RecUserService;
import com.vs.vision.pojo.rec.RecActivityPush;
import com.vs.vision.pojo.rec.RecPayUser;
import com.vs.vision.pojo.sys.Users;

@RestController
@RequestMapping("/recUser")
public class RecUserController {
	@Autowired
	private RecUserService recUserService;
	@RequestMapping("/findObjectByUserIdAndParentId")
	public List<RecPayUser> findObjectByUserIdAndParentId(@RequestBody Users user){
		System.out.println("后台准备查询指定门店下的用户充值记录："+user);
		return recUserService.findObjectByUserIdAndParentId(user);
	}
	@RequestMapping("/doFindActivityObjectByUserPayType")
	public RecActivityPush doFindActivityObjectByUserPayType(Integer id) {
		return recUserService.doFindActivityObjectByUserPayType(id);
	}
}
