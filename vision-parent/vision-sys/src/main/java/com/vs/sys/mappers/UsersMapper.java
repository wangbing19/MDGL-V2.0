package com.vs.sys.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vs.vision.pojo.sys.Users;
import com.vs.vision.vo.Node;

public interface UsersMapper {
	 /**
	  * 基于用户名查询记录总数
	  * @param username
	  * @return
	  */
	 int getRowCount(@Param("username")String username);
	 /**
	  * 基于用户名查询当前页记录
	  * @param username 查询条件
	  * @param startIndex 起始页位置
	  * @param pageSize 页面大小
	  * @return 当前页记录
	  */
	 List<Users> findPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 List<Users> findPageObjectsByParentId(
			 @Param("username")String username,
			 @Param("parentId")Integer parentId,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 List<Users> searchPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 
	 List<Users> findUserByUserName();
	 Users findUserByUserName(String username);
	 
	 int doValidById(
			 @Param("id")Integer id,
			 @Param("valid")Integer valid,
			 @Param("modifiedUser")String modifiedUser
			 );
	 List<Node> findZTreeNodes();
	 int doinsertObject(Users Users);
	 Users doFindObjectById(Integer id);
	 int doUpdateObject(Users Users);
}