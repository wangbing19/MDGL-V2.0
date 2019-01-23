package com.vs.service.ppo;

import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.PageObject;

public interface PpoAppointmentservice {

    PageObject<PpoAppointment> doFindPageObjects(String appointmentName, Integer pageCurrent);
}
