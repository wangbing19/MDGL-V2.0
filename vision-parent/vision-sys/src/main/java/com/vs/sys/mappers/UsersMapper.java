package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}