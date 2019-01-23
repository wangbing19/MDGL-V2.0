package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Long id);

    Roles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}