package com.vs.sys.service;

import com.vs.sys.mappers.DeptsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DpetServiceImpl implements DeptService {
	@Autowired
	private DeptsMapper deptMapper;

	@Override
	public List<Map<String, Object>> findObjects() {
		return deptMapper.findObjects();
	}
}
