package com.vs.rec.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.rec.RecPayUser;

public interface RecUserMapper extends BaseMapper<RecPayUser>{
	@Select("SELECT * FROM rec_pay_user WHERE NAME= #{name} ORDER BY id DESC LIMIT 0,1")
	RecPayUser findLastPayTime(@Param("name") String name);

}
