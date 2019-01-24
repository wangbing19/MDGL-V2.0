package com.vs.pre.service;

import java.util.List;

import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.vo.DiagnosisDate;
import com.vs.vision.vo.Node;

public interface DiagnosisResultService {

	List<DiagnosisResult> doFindObjects();

	String deleteResultObjectById(Integer diagnosisId);

	List<Node> findZtreeMenuNodes();

	String findParentNameById(Integer id);

	String updateObject(DiagnosisDate diagnosisDate);

	DiagnosisResult findObjectById(Integer id);

	String updateDisTypeById(Integer disType, Integer id);

	String insertObject(DiagnosisResult diagnosisResult);



}
