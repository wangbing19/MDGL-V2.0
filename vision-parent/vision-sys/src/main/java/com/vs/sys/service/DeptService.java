package com.vs.sys.service;

import java.util.List;
import java.util.Map;

import com.vs.vision.pojo.sys.Depts;
import com.vs.vision.vo.Node;

public interface DeptService {
	int saveObject(Depts entity);

	int updateObject(Depts entity);

	List<Node> findZTreeNodes();

	List<Map<String, Object>> findObjects();

	int deleteObject(Integer id);
}
