package com.vs.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vs.sys.mappers.RoleMenusMapper;
import com.vs.sys.mappers.RolesMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.sys.Roles;
import com.vs.vision.vo.CheckBox;
import com.vs.vision.vo.PageObject;
import com.vs.vision.vo.sys.SysRoleMenuResult;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RolesMapper rolesMapper;

	@Autowired
	private RoleMenusMapper roleMenusMapper;

	@Override
	public List<CheckBox> findObjects() {
		return rolesMapper.findObjects();
	}

	@Override
	public SysRoleMenuResult findObjectById(Integer id) {
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		SysRoleMenuResult result = rolesMapper.findObjectById(id);
		if (result == null)
			throw new ServiceException("记录可能已经不存在");
		return result;
	}

	@Override
	public int updateObject(Roles entity, Integer[] menuIds) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("名字不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色赋予权限");
		//SysUser user = ShiroUtils.getUser();
		entity.setModifiedUser("admin");
		int rows = rolesMapper.updateObject(entity);
		roleMenusMapper.deleteObjectsByRoleId(entity.getId());
		roleMenusMapper.insertObject(entity.getId(), menuIds);
		return rows;
	}

	@Override
	public int saveObject(Roles entity, Integer[] menuIds) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("名字不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色赋予权限");
		//SysUser user = ShiroUtils.getUser();
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		int rows = rolesMapper.insertObject(entity);
		System.out.println(rows);
		return rows;
	}

	@Override
	public int deleteObject(Long id) {
		System.out.println(id);
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值不合法");
		int rows = rolesMapper.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		roleMenusMapper.deleteObjectsByRoleId(id);
		return rows;
	}

	@Override
	public PageObject<Roles> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		int rowCount = rolesMapper.getRowCount(username);
		if (rowCount == 0)
			throw new ServiceException("记录不存在");
		int pageSize = 20;// 页面大小
		int startIndex = (pageCurrent - 1) * pageSize;// 起始位置
		List<Roles> records = rolesMapper.findPageObjects(username, startIndex, pageSize);
		PageObject<Roles> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		int pageCount = (rowCount - 1) / pageSize + 1;
		pageObject.setPageCount(pageCount);
		return pageObject;
	}
}
