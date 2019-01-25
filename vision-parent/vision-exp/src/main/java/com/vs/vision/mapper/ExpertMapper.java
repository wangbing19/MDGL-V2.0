package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.Expert;
import com.vs.vision.vo.Node;

public interface ExpertMapper extends BaseMapper<Expert>{
	/**
	 * 与远程诊断表并用,通过专家表id查找专家姓名
	 * @param id
	 * @return
	 */
	Expert findId(Integer expertId);

	Node[] selectExpName();

	/**
	 * 通过专家姓名查询专家表总条数
	 * @param expertName
	 * @return
	 */
	int countExp(@Param("expertName")String expertName);

	/**
	 * 基于条件进行分页查询
	 * @param expertName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Expert> limitExp(
			@Param("expertName") String expertName,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
}
