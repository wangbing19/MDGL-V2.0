package com.vs.vision.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService{

	@Override
	public String doDeptListUI() {
		// TODO Auto-generated method stub
		return "pages/sys/sys_dept_list";
	}

}
