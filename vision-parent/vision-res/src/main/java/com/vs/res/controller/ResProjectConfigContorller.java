package com.vs.res.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vs.res.servise.ResSymptomTypeSvervise;
import com.vs.vision.pojo.res.ResSymptomType;

@RestController
public class ResProjectConfigContorller {
	@Autowired
	ResSymptomTypeSvervise resSymptomTypeSvervise;
	
	@RequestMapping("/findAll")
	public List<ResSymptomType> findObjects(Integer userId){
		try {
			List<ResSymptomType> result=	resSymptomTypeSvervise.findObjects(userId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
