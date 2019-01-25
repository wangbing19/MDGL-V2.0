package com.vs.ppo.service;

import java.util.List;

import com.vs.vision.pojo.ppo.vo.PpoAppTime;

public interface PpoAppointmentTimeservice {

	Integer saveTime(PpoAppTime ppoAppTime);

	Integer updateTime(PpoAppTime ppoAppTime);

	List<PpoAppTime> findTime(PpoAppTime ppoAppTime);

}
