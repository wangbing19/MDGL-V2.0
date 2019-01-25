package com.vs.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vs.sys.mappers.MenusMapper;
import com.vs.sys.mappers.RoleMenusMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.sys.Menus;
import com.vs.vision.vo.Node;

/**
 * 声明式事务控制(底层借助代理机制控制事务) 1)基于xml(了解) 2)基于注解(趋势,例如spring boot)
 * 注解方式的声明式事务控制需要借助@Transactional 注解对类或方法进行描述。
 */
@Transactional(rollbackFor = Throwable.class, timeout = 5, isolation = Isolation.READ_COMMITTED)
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenusMapper MenusMapper;
	@Autowired
	private RoleMenusMapper roleMenusMapper;

	@Transactional(timeout = 3)
	@Override
	public int updateObject(Menus entity) {
		if (entity == null)
			throw new IllegalArgumentException("参数异常");
		if (StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		entity.setModifiedUser("admin");
		int rows = MenusMapper.updateObject(entity);
		// 4.返回结果
		return rows;
	}

	@Override
	public int saveObject(Menus entity) {
		if (entity == null)
			throw new IllegalArgumentException("参数异常");
		if (StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		int rows = MenusMapper.insertObject(entity);
		return rows;
	}

	/**
	 * 假如是读事务，建议readOnly属性的值为true， readOnly默认为false
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = MenusMapper.findZtreeMenuNodes();
		if (list == null || list.size() == 0)
			throw new ServiceException("记录不存在");
		return list;
	}

	@Override
	public int deleteObject(Integer id) {
		// 1.验证参数有效性
		if (id == null || id < 1)
			throw new IllegalArgumentException("参数id无效");
		// 2.统计菜单是否有子菜单，并进行验证
		int rowCount = MenusMapper.getChildCount(id);
		if (rowCount > 0)
			throw new ServiceException("请先删除子菜单");
		// 3.删除当前菜单信息
		int rows = MenusMapper.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		// 4.删除菜单角色的关系数据
		/* int count= */
		roleMenusMapper.deleteObjectsByMenuId(id);
		// if(count>0)throw new ServiceException("关系数据删除失败");
		return rows;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = MenusMapper.findObjects();
		if (list == null || list.size() == 0)
			throw new ServiceException("没有对应数据");
		return list;
	}

}
