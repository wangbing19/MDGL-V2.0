package com.vs.sys.service;

import java.util.List;

import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.vo.CheckBox;
import com.vs.vision.vo.PageObject;
import com.vs.vision.vo.sys.SysRoleMenuResult;

public interface RoleService {
	/**
	 * 获取所有角色的id和name属性值
	 * @return
	 */
	List<CheckBox> findObjects();
	/**
	 * 基于角色id获取角色以及角色对应的菜单信息
	 * @param id
	 * @return
	 */
	SysRoleMenuResult findObjectById(Integer id);
	/**
	 * 保存角色和角色对应的菜单数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(Roles entity,Integer[]menuIds);
	 /**
	  * 保存角色和角色对应的菜单数据
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int saveObject(Roles entity,Integer[]menuIds);
	 /**
	  * 基于角色id删除角色相关信息
	  * @param id
	  * @return
	  */
	 int deleteObject(Long id);
	 
     /**
      * 通过此方法执行日志分页查询
      * 1)获取总记录数
      * 2)获取当前页的记录
      * 3)计算总页数
      * 4)封装查询结果
      * @param name 查询条件(基于用户名查询日志行为数据)
      * @param pageCurrent 分页条件(当前页的页码值)
      * @return
      */
	 PageObject<Roles> findPageObjects(
			 String name,
			 Integer pageCurrent);

}
