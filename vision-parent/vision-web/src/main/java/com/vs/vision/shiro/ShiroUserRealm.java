package com.vs.vision.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.catalina.mbeans.UserMBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vs.vision.mapper.MenusMapper;
import com.vs.vision.mapper.RoleMenusMapper;
import com.vs.vision.mapper.UserRoleMapper;
import com.vs.vision.mapper.UsersMapper;
import com.vs.vision.pojo.sys.Users;
import com.vs.vision.utils.ShiroUtils;

/**
 * 负责用户认证信息和授权信息的获取以及封装
 * 
 * @author ta
 */
@Service
@Scope("singleton")
public class ShiroUserRealm extends AuthorizingRealm {

	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMenusMapper roleMenusMapper;
	@Autowired
	private MenusMapper menusMapper;

	/**
	 * 设置凭证(密码)匹配器
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		cMatcher.setHashAlgorithmName("MD5");
		super.setCredentialsMatcher(cMatcher);
	}

	/**
	 * 负责认证信息的获取和封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取登录用户信息
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		// 2.基于用户名从数据库查询用户并验证
		Users user = usersMapper.findUserByUserName(uToken.getUsername());
		// 2.1验证用户是否存在
		if (user == null)
			throw new UnknownAccountException();
		// 2.2验证用户是否被禁用
		if (user.getValid() == 0)
			throw new LockedAccountException();
		// 3.封装用户信息并返回
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
		return info;// 返回给认证管理器
	}

	private Map<String, AuthorizationInfo> authorizationCache = new ConcurrentHashMap<>();

	/** 负责授权信息的获取和封装 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1.获取登录用户信息
		Users user = (Users) principals.getPrimaryPrincipal();
	//	AuthorizationInfo aInfo ;

		// 2.基于登录用户id获取对应的角色id
		List<Integer> roleIds = userRoleMapper.findRoleIdsByUserId(user.getId());
		if (roleIds == null || roleIds.size() == 0)
			throw new AuthorizationException();
		// 3.基于用户角色获得对应的菜单id
		Integer[] array = {};
		List<Integer> menuIds = roleMenusMapper.findMenuIdsByRoleIds(roleIds.toArray(array));
		if (menuIds == null || roleIds.size() == 0)
			throw new AuthorizationException();
		// 4.基于菜单id获取菜单对应的权限标识
		List<String> permissionList = menusMapper.findPermissions(menuIds.toArray(array));
		if (permissionList == null || permissionList.size() == 0)
			throw new AuthorizationException();
		// 5.对用户权限进行封装并返回(授权管理器)
		Set<String> stringPermissions = new HashSet<>();
		for (String p : permissionList) {
			if (!StringUtils.isEmpty(p)) {
				stringPermissions.add(p);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(stringPermissions);
		return info;// 返回给授权管理器
	}

	/***
	 * 执行退出操作
	 */
	public void logout() {
		Users user = ShiroUtils.getUser();
		authorizationCache.remove(user.getUsername());
		// 系统底层会将用户从shiro中的session对象移除
		SecurityUtils.getSubject().logout();
	}
}
