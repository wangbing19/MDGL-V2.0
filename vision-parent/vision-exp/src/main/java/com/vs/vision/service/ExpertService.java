package com.vs.vision.service;

import java.util.List;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.Expert;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

public interface ExpertService {

	Node[] selectExpName();

	PageObject<Expert> limitExp(String expertName, Integer pageCurrent);

	Expert doSelectExp(Integer id);

	Integer doUpdateExp(Expert entity);

	Integer doDeleteExp(Integer[] ids);

	Integer doSaveObject(Expert entity);

	Integer doUpdateMessage(Expert entity);

}
