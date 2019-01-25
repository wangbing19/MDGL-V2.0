package com.vs.sys.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vs.vision.pojo.sys.Logs;

public interface LogsMapper {
	 /**
	  * 将用户行为日志写入到数据库
	  * @param entity
	  * @return
	  */
	 int insertObject(Logs entity);
	
	 /**
	  * 执行日志删除操作
	  * @param ids 请求参数(代表一个或多个
	  * 日志记录的id)
	  * @return 删除的记录行数
	  * 说明使用@Param的目的是为参数定义一个
	  * 参数名，后续可以在映射文件中直接使用
	  * 此名字
	  */
	 int deleteObjects(@Param("ids")Integer... ids);
	
    /**
     * 基于条件(用户名)查询系统中的总记录数，
     * 我们会在业务层基于总记录数进行总页数
     * 的计算操作
     * @param username
     * @return
     */
	 int getRowCount(@Param("username")String username);
	 /**
	  * 基于条件查询当前页要显示的记录
	  * @param username 查询条件
	  * @param startIndex 当前页的起始位置
	  * @param pageSize 页面大小(每页要显示的记录数)
	  * @return
	  */
	 List<Logs> findPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
}