package com.vs.rec.service;

import java.util.List;

import com.vs.vision.pojo.rec.RecActivityPush;

public interface ActivityPushService {

	List<RecActivityPush> findObjects(Integer activityState);

	String deleteObjectById(Integer id);

}
