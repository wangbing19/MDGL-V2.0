package com.vs.sys.service;

import java.util.List;

import com.vs.vision.pojo.sys.Users;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

public interface UserService {
	
	/**
	 * 基于条件分页查询用户信息
	 * 
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<Users> findPageObjects(String username, Integer pageCurrent);
	PageObject<Users> searchPageObjects(String username, Integer pageCurrent);

	List<Users> findUserByUserName();

	int doValidById(Integer id, Integer valid);

	List<Node> findZTreeNodes();

	int doSaveObject(Users Users);

	Users doFindObjectById(Integer id);
	
	int doUpdateObject(Users Users);

}
