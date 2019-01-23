package com.vs.sys.mappers;

import com.vs.vision.pojo.sys.Logs;

public interface LogsMapper {
    int deleteByPrimaryKey(Long id);

    Logs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}