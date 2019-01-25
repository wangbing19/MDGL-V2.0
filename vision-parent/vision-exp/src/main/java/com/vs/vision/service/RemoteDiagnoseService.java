package com.vs.vision.service;

import java.util.Map;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.vo.PageObject;

public interface RemoteDiagnoseService {

    PageObject<ExpRemoteDiagnoseVo> findPageObjects(String customerName, Integer pageCurrent);

	ExpRemoteDiagnoseVo select(Integer id);

	Integer validById(Integer id,Integer valid);

	Integer doDelete(Integer[] ids);

	Integer doSaveObject(RemoteDiagnose entity);

	Integer doUpdate(RemoteDiagnose entity);
}
