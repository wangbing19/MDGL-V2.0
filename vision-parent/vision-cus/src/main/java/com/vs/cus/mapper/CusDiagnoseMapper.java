package com.vs.cus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.cus.CusDiagnose;

public interface CusDiagnoseMapper extends BaseMapper<CusDiagnose> {
	
	/**
	 * 依据条件获取总记录数并进行验证
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return
	 */
	int getRowCount(@Param("userId")Integer userId, @Param("userParentId")Integer userParentId);
	
	/**
	 * 诊断表页面加载,查询
	 * @param startIndex	起始位置
	 * @param pageSize	页面大小
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return	当前页记录
	 */
	List<CusDiagnose> findPageObjects(
			@Param("userId")Integer userId, 
			@Param("userParentId")Integer userParentId, 
			@Param("startIndex")int startIndex, 
			@Param("pageSize")int pageSize);
}
