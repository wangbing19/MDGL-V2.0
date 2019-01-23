package com.vs.sys.mappers;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.sys.Depts;

public interface DeptsMapper extends BaseMapper<Depts>{
	List<Map<String,Object>> findObjects();
}