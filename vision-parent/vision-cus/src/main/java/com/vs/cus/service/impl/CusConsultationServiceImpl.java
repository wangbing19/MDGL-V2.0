package com.vs.cus.service.impl;

import com.vs.cus.mapper.CusConsultationMapper;
import com.vs.cus.service.CusConsultationService;
import com.vs.vision.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CusConsultationServiceImpl implements CusConsultationService {
  //  @Autowired
    private CusConsultationMapper cusConsultationMapper;


    @Override
    public JsonResult findPageObjects(Map<String, String> map) {
        String name = map.get("name");
        String tel = map.get("tel");
        Integer pageCurrent = Integer.valueOf(map.get("pageCurrent"));

        return null;

    }
}
