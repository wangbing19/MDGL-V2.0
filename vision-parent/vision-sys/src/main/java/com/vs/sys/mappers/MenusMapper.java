package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.Menus;

public interface MenusMapper {
    int deleteByPrimaryKey(Integer id);

    Menus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}