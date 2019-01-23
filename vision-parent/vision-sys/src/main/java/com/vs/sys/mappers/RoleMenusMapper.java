package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.sRoleMenus;

public interface RoleMenusMapper {
    int deleteByPrimaryKey(Integer id);

    sRoleMenus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(sRoleMenus record);

    int updateByPrimaryKey(sRoleMenus record);
}