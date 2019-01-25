package com.vs.sys.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.vo.CheckBox;
import com.vs.vision.vo.sys.SysRoleMenuResult;

public interface RolesMapper {
	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<CheckBox> findObjects();
	/**
	 * 基于id查询角色以及对应的菜单信息
	 * @param id
	 * @return
	 */
	SysRoleMenuResult findObjectById(Integer id);
	/**
	 * 更新角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(Roles entity);
	
	/**
	 * 保存角色自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(Roles entity);
	/**
	 * 删除角色自身信息
	 * @param id
	 * @return
	 */
	int deleteObject(Long id);
    /**
     * 依据条件统计角色个数
     * @param name
     * @return
     */
	int getRowCount(@Param("name")String name);
	/**
	 * 依据条件查询角色当前页信息
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Roles> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
}