package com.vs.pre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vs.pre.mapper.DiagnosisResultMapper;
import com.vs.vision.pojo.pre.DiagnosisResult;
@Service
public class DiagnosisResultServiceImpl implements DiagnosisResultService{
	@Autowired
	private DiagnosisResultMapper diagnosisResultMapper;
	@Override
	public List<DiagnosisResult> doFindObjects() {
		System.out.println("我是实现类");
		return diagnosisResultMapper.selectList(null);
	}
	
}
