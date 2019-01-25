package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.SymptomsDescribed;

public interface SymptomsDescribedMapper extends BaseMapper<SymptomsDescribed>{

	/**
	 * 通过远程诊断表的id查询到症状描述表的id(与远程诊断删除数据关联)
	 * 意义:在远程诊断删除一条数据后将这条数据对应的症状描述数据一起删除
	 * @param id 远程诊断表的id
	 * @return
	 */
	List<Integer> selectSymId(@Param("ids")Integer... ids);

}
