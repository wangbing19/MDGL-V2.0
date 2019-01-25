package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface RoleMenusMapper {
	/**
	 * 基于角色id获取菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer...roleIds);

	/**
	 * 保存角色和菜单关系数据
	 * @param userId
	 * @param menuIds
	 * @return
	 */
	int insertObject(
			@Param("roleId")Long userId,
			@Param("menuIds")Integer[] menuIds);
	
	/**
	  * 基于角色id删除角色自身信息
	  * @param id
	  * @return
	  */
	 int deleteObjectsByRoleId(Long id);
	/**
	 * 基于菜单id删除角色菜单关系表中的数据
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);


}