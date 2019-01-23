package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}