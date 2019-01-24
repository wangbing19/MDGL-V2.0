package com.vs.vision.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.Expert;

public interface ExpertMapper extends BaseMapper<Expert>{
	/**
	 * 与远程诊断表并用,通过专家表id查找专家姓名
	 * @param id
	 * @return
	 */
	Expert findId(Integer expertId);
}
