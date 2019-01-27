package com.vs.ppo.service;




import java.util.List;

import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.PageObject;

public interface PpoAppointmentservice {

    PageObject< PpoAppointment > doFindPageObjects(String appointmentName, Integer pageCurrent);

	void saveppoAppointmentAll(PpoAppointment ppoAppointment);

	PpoAppointment findAppointmentById(PpoAppointment ppoAppointment);

	Integer doupdateAppointment(PpoAppointment ppoAppointment);

	Integer dodeleteAppointment(PpoAppointment ppoAppointment);

	List<PpoAppointment> findAppointmentName(Integer userId);

	
}
