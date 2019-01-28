package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.pra.TraInformationrecord;

public interface TraInformationrecordMapper extends BaseMapper<TraInformationrecord>{
//	
//	List<TraInformationrecord> findPageObjects(
//			@Param("name") String name,
//			@Param("startIndex")Integer startIndex,
//			@Param("pageSize")Integer pageSize,
//			@Param("userParentId")Integer userParentId);
	
	
	/**
	 * 基于用户名/电话查询当前页记录
	 * @param name	查询条件
	 * @param startIndex	起始位置
	 * @param pageSize	页面大小
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return	当前页记录
	 */
	List<TraInformationrecord> findPageObjects(
			@Param("name")String name, 
			@Param("startIndex")Integer startIndex, 
			@Param("pageSize")Integer pageSize, 
			@Param("userId")Integer userId, 
			@Param("userParentId")Integer userParentId);
	
	
	
	/**
	 * 基于用户名查询记录总数
	 * @param name	查询条件
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return
	 */
	int getRowCount(@Param("name")String name, @Param("userId")Integer userId,@Param("userParentId") Integer userParentId);


	TraInformationrecord selectId(Integer id);
}
