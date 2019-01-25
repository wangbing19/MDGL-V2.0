package com.vs.sys.service;

import java.util.List;
import java.util.Map;

import com.vs.vision.pojo.sys.Menus;
import com.vs.vision.vo.Node;

public interface MenuService {
	/**
	 * 将菜单信息更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(Menus entity);
	
	 /**
	  * 将菜单信息保存到数据库
	  * @param entity
	  * @return
	  */
	 int saveObject(Menus entity);
	 
	
	 List<Node> findZtreeMenuNodes();
	
	 List<Map<String,Object>> findObjects();
	 /**
	  * 基于菜单id删除菜单以及菜单与角色的关系数据
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);

}
