package com.vs.ppo.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.ppo.PpoAppointment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface PpoAppointmentMapper extends BaseMapper<PpoAppointment> {
	
    List<PpoAppointment> findAppointmentList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    				
}
