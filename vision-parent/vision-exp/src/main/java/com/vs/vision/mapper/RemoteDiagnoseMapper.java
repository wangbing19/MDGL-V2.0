package com.vs.vision.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface RemoteDiagnoseMapper extends BaseMapper<RemoteDiagnose> {

	/**
	 *不输入客户名字的话默认是查询出全部信息
	 * @param username 客户名字
	 * @param startIndex 当前页的第一个信息对应的门店诊断序号
	 * @param pageSize 每一页显示的数量
	 * @return
	 */
	List<ExpRemoteDiagnoseVo> findPageObjects(
			@Param("customerName") String customerName,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
			//@Param("registerParentid")Integer registerParentid);

	/**
	 * 查询对应客户姓名的数量,如果没输入客户姓名则查询远程诊断表中的所有数量
	 * @param customerName:客户姓名
	 * @return
	 */
	int getRowCount(
			@Param("customerName") String customerName);
			//@Param("registerParentid")Integer registerParentid);

	/**
	 * 通过选择的id在修改页面获取远程诊断表对应id中的信息
	 * @param id
	 * @return
	 */
	ExpRemoteDiagnoseVo select(Integer id);

	/**
	 * 对应添加作用
	 * @param entity:从浏览器获取的添加信息
	 * @return
	 */
	Integer insertObject(RemoteDiagnose entity);

}
