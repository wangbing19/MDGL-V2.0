package com.vs.vision.service;

import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.vo.PageObject;

public interface TraInformationrecordService {

	PageObject<TraInformationrecord> findPageObjects(String name, Integer pageCurrent, Integer userParentId);

	Integer doSaveObject(TraInformationrecord entity);

	Integer doDeleteObject(Integer id);

	TraInformationrecord doSelect(Integer id);

	Integer doUpdate(TraInformationrecord entity);


}
