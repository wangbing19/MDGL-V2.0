package com.vs.rec.service;

import java.util.List;

import com.vs.vision.pojo.rec.RecActivityPush;
import com.vs.vision.pojo.rec.RecPayUser;
import com.vs.vision.pojo.sys.Users;

public interface RecUserService {

	List<RecPayUser> findObjectByUserIdAndParentId(Users user);

	RecActivityPush doFindActivityObjectByUserPayType(Integer id);

	String insertObjectRecUser(RecPayUser recPayUser);

}
