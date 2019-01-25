package com.vs.sys.mappers;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.sys.Depts;
import com.vs.vision.vo.Node;

public interface DeptsMapper extends BaseMapper<Depts>{
	int updateObject(Depts entity);
	int insertObject(Depts entity);
	List<Node> findZTreeNodes();
	List<Map<String,Object>> findObjects();
	int getChildCount(Integer id);
	int deleteObject(Integer id);
}