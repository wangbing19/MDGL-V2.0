package com.vs.cus.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.vs.vision.pojo.cus.CusConsultation;

public interface CusConsultationMapper extends BaseMapper<CusConsultation> {

	/**
	 * 基于用户名/电话查询当前页记录
	 * @param name	查询条件
	 * @param tel	查询条件
	 * @param startIndex	起始位置
	 * @param pageSize	页面大小
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return	当前页记录
	 */
	List<CusConsultation> findPageObjects(
			@Param("name")String name,
			@Param("tel")String tel,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize,
			@Param("userId") Integer userId,
			@Param("userParentId")Integer userParentId
			);

	/**
	 * 基于用户名查询记录总数
	 * @param name	查询条件
	 * @param tel	查询条件
	 * @param userId	门店id
	 * @param userParentId	上级门店id
	 * @return
	 */
	int getRowCount(
			@Param("name")String name,
			@Param("tel")String tel, 
			@Param("userId")Integer userId, 
			@Param("userParentId")Integer userParentId);

	/**
	 * 基于咨询表id更改用户信息
	 * @param cusConsultation
	 * @return
	 */
	int updateObject(CusConsultation cusConsultation);
}
