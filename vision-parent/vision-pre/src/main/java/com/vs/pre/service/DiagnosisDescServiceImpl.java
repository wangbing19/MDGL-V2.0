package com.vs.pre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vs.pre.mapper.DiagnosisDescMapper;
import com.vs.vision.pojo.pre.DiagnosisDesc;

@Service
public class DiagnosisDescServiceImpl implements DiagnosisDescService{
	@Autowired
	private DiagnosisDescMapper diagnosisDescMapper;

	@Override
	public DiagnosisDesc findDescObjectById(Integer id) {
		DiagnosisDesc descObject = diagnosisDescMapper.selectById(id);
		System.out.println("后台根据症状id查询的症状描述："+descObject);
		return descObject;
	}	
}
