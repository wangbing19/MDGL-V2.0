package com.vs.service.ppo;

import com.vs.mapper.ppo.PpoAppointmentMapper;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PpoAppointmentserviceImpl implements PpoAppointmentservice {
    @Autowired
   private PpoAppointmentMapper ppoAppointmentMapper;


    @Override
    public PageObject<PpoAppointment> doFindPageObjects(String appointmentName, Integer pageCurrent) {
        Integer count = ppoAppointmentMapper.selectCount(null);

        int pageSize=3;
        int startIndex=(pageCurrent-1)*pageSize;
        List<PpoAppointment> records = ppoAppointmentMapper.findAppointmentList(startIndex, pageSize);
        PageObject<PpoAppointment> pageObject = new PageObject<PpoAppointment>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        int pageCount=(count-1)/pageSize+1;
        System.out.println(pageCount);
        pageObject.setPageCount(pageCount);
        return pageObject;

    }
}
