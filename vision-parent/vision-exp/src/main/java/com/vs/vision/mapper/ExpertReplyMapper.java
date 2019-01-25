package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.ExpertReply;

public interface ExpertReplyMapper extends BaseMapper<ExpertReply>{

	/**
	 * 通过远程诊断表的id查询到专家回复表的id,在远程诊断实现类中的删除使用
	 * @param id远程诊断表的id
	 * @return
	 */
	List<Integer> selectExpId(@Param("ids")Integer... ids);

}
