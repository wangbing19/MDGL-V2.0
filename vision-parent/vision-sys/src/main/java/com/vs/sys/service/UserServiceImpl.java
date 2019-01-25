package com.vs.sys.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.vs.sys.mappers.UsersMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

@Controller
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersMapper UsersMapper;

	@Override
	public PageObject<Users> findPageObjects(String username, Integer pageCurrent) {
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
		List<Users> records = UsersMapper.findPageObjects(username, startIndex, pageSize);

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
	public int doValidById(Integer id, Integer valid) {
		if (id == null || id < 1)
			throw new IllegalArgumentException("id值无效");
		if (valid != 0 && valid != 1)
			throw new IllegalArgumentException("状态值不正确");
		//Users user = ShiroUtils.getUser();
		//String username = user.getUsername();
		int rows = UsersMapper.doValidById(id, valid, "admin");
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
	public int doSaveObject(Users entity) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if (StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if (entity.getRole() == null)
			throw new IllegalArgumentException("必须指定其角色");
		
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		//SimpleHash hash = new SimpleHash("MD5", entity.getPassword(), salt, 1);
		String md5DigestAsHex = DigestUtils.md5DigestAsHex((entity.getPassword()+salt).getBytes());
		entity.setPassword(md5DigestAsHex);
		// 保存用户自身信息
		//Users user = ShiroUtils.getUser();
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		// 保存用户自身信息
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
		
		// 保存用户自身信息
		//Users user = ShiroUtils.getUser();
		entity.setModifiedUser("admin");
		
		// 保存用户自身信息
		int doSaveObject = UsersMapper.doUpdateObject(entity);

		return doSaveObject;
	}

}
