package com.vs.vision.utils;

import org.apache.shiro.SecurityUtils;

import com.vs.vision.pojo.sys.Users;



public class ShiroUtils {

	 public static Users getUser(){
		 return (Users)SecurityUtils
		.getSubject().getPrincipal();
	 }
}
