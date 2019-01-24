package com.vs.vision.service;

import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.vo.PageObject;

public interface RemoteDiagnoseService {

    PageObject<ExpRemoteDiagnoseVo> findPageObjects(String customerName, Integer pageCurrent);
}
