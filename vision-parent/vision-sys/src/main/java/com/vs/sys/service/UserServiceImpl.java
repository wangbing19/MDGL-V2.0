package com.vs.sys.service;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.vs.sys.mappers.UsersMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

@Controller
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper UsersMapper;

	@Override
	public PageObject<Users> findPageObjects(String username, Integer pageCurrent, Users user) {
		// 1.验证参数有效性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		// 2.基于条件查询总记录数并进行验证
		int rowCount = UsersMapper.getRowCount(username);
		if (rowCount == 0)
			throw new ServiceException("没有找到对应记录");
		// 3.基于条件查询当前页记录
		int pageSize = 15;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<Users> records;
		if (user.getRole() == 10) {
			records = UsersMapper.findPageObjectsByParentId(username, user.getId(), startIndex, pageSize);
		} else {
			records = UsersMapper.findPageObjects(username, startIndex, pageSize);
		}

		// 4.对查询结果进行封装并返回
		PageObject<Users> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		return pageObject;
	}

	@Override
	public PageObject<Users> searchPageObjects(String username, Integer pageCurrent) {
		// 1.验证参数有效性
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		// 2.基于条件查询总记录数并进行验证
		int rowCount = UsersMapper.getRowCount(username);
		if (rowCount == 0)
			throw new ServiceException("没有找到对应记录");
		// 3.基于条件查询当前页记录
		int pageSize = 15;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<Users> records = UsersMapper.searchPageObjects(username, startIndex, pageSize);

		// 4.对查询结果进行封装并返回
		PageObject<Users> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		return pageObject;
	}

	@Override
	public List<Users> findUserByUserName() {
		List<Users> users = UsersMapper.findUserByUserName();
		return users;
	}

	@Override
	public int doValidById(Integer id, Integer valid, String username) {
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		if (valid != 0 && valid != 1)
			throw new IllegalArgumentException("状态值不正确");
		// Users user = ShiroUtils.getUser();
		// String username = user.getUsername();
		int rows = UsersMapper.doValidById(id, valid, username);
		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public List<Node> findZTreeNodes() {
		List<Node> findZTreeNodes = UsersMapper.findZTreeNodes();

		return findZTreeNodes;
	}

	@Override
	public int doSaveObject(Users user, Users entity) {
		System.out.println(user);
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (entity.getRole() == null)
			throw new IllegalArgumentException("必须指定其角色");
		if (user.getRole() == 9 || user.getRole() == 11)
			throw new IllegalArgumentException("该账号没有创建门店的权限");
		if (entity.getRole() == 8)
			throw new IllegalArgumentException("角色选择错误，不能创建超级管理员");
		if (user.getRole() == 10) {
			if (entity.getRole() != 11 || entity.getRole() == null) {
				throw new IllegalArgumentException("角色选择错误，请选择连锁门店角色");
			}
			Users Users = UsersMapper.doFindObjectById(user.getId());
			if (Users.getDeptLimit() <= Users.getDeptNum()) {
				throw new IllegalArgumentException("已经达到创建门店上限，请联系系统管理员");
			}
			entity.setParentId(user.getId());
		}
		if (user.getRole() == 12) {
			if (user.getRole() != 8 && entity.getRole() != 9 && entity.getRole() != 10 && entity.getRole() != 11) {
				throw new IllegalArgumentException("创建角色错误，不能创建管理员账号");
			}
		}
		if(entity.getRole()==10) {
			entity.setParentId(1);
		}
		if (entity.getRole() != 8 && entity.getRole() != 12 && entity.getParentId() == null) {
			throw new IllegalArgumentException("上级部门不能为空");
		}
		if (StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if (StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		Users parentUser = UsersMapper.doFindObjectById(entity.getParentId());
		if (entity.getRole() == 12) {
			entity.setParentId(null);
		} else {
			entity.setParentUsername(parentUser.getUsername());
		}
		if (entity.getRole() == 10) {
			entity.setDeptNum(0);
			if (entity.getDeptLimit() == null) {
				// 创建连锁门店，下级门店上限为空时默认设置为10
				entity.setDeptLimit(10);
			}
			if (entity.getCustomerLimit() == null) {
				// 创建连锁门店，顾客档案数量上限为空时默认设置为200
				entity.setCustomerLimit(200);
			}
		}
		if (entity.getRole() == 9) {
			if (entity.getCustomerLimit() == null) {
				// 创建普通门店，顾客档案上限数字为空时默认设置为200
				entity.setCustomerLimit(500);
			}
			entity.setDeptLimit(null);
		}
		Users userByUserName = UsersMapper.findUserByUserName(entity.getUsername());
		if (userByUserName != null) {
			throw new IllegalArgumentException("用户名已存在，请重试！");
		}
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		SimpleHash hash = new SimpleHash("MD5", entity.getPassword(), salt, 1);
		entity.setPassword(hash.toHex());
		entity.setCreatedUser(user.getUsername());
		entity.setModifiedUser(user.getUsername());
		if (entity.getValid() == null) {
			entity.setValid(0);
		}
		// 更新连锁门店数量
		if (entity.getRole() == 11) {
			entity.setDeptLimit(null);
			entity.setCustomerLimit(null);
			parentUser.setDeptNum(parentUser.getDeptNum() + 1);
		}
		UsersMapper.doUpdateObject(parentUser);
		int doSaveObject = UsersMapper.doinsertObject(entity);

		return doSaveObject;
	}

	@Override
	public Users doFindObjectById(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("参数值无效");
		// 2.查询用户以及对应的部门信息
		Users result = UsersMapper.doFindObjectById(id);
		return result;
	}

	@Override
	public int doUpdateObject(Users entity) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if (entity.getRole() == null)
			throw new IllegalArgumentException("必须指定其角色");

		int doSaveObject = UsersMapper.doUpdateObject(entity);

		return doSaveObject;
	}

}
