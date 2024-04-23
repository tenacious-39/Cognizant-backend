package com.vitacure.doctorservice.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitacure.doctorservice.exception.ParseDateException;
import com.vitacure.doctorservice.exception.ResourceNotFoundException;
import com.vitacure.doctorservice.feign.MedicineFeignClient;
import com.vitacure.doctorservice.model.Schedule;
import com.vitacure.doctorservice.model.ScheduleWrapper;
import com.vitacure.doctorservice.repository.ScheduleRepository;
import com.vitacure.doctorservice.service.ScheduleService;
import com.vitacure.doctorservice.util.ScheduleUtil;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scRepo;
	
	@Autowired
	private MedicineFeignClient medFeignClient;
	
	@Override
	public List<ScheduleWrapper> getScheduleByDoctorId(int doctorId) throws ResourceNotFoundException {
		List<Schedule> schedules = scRepo.findAllByDoctorDoctorId(doctorId);
		if(schedules == null || schedules.isEmpty()) {
			throw new ResourceNotFoundException("Doctor Id", doctorId);
		}
		Map<String, String> medicineNames = 
				ScheduleUtil.extractMedicineNamesFromSchedule(schedules, medFeignClient);
		List<ScheduleWrapper> response = 
				ScheduleUtil.convertScheduleListToScheduleWrapperList(schedules, medicineNames);
		return response;
	}
	
	@Override
	public Schedule addSchedule(Schedule schedule) {
		return scRepo.save(schedule);
	}
	
	@Override
	public List<ScheduleWrapper> getScheduleByDate(String dateStr) throws ParseDateException, 
																		ResourceNotFoundException {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			throw new ParseDateException(dateStr);
		}
		List<Schedule> schedules = scRepo.findAllByDate(date);
		
		if(schedules == null || schedules.isEmpty()) {
			throw new ResourceNotFoundException("Date", dateStr);
		}
		
		Map<String, String> medicineNames = 
				ScheduleUtil.extractMedicineNamesFromSchedule(schedules, medFeignClient);
		List<ScheduleWrapper> response = 
				ScheduleUtil.convertScheduleListToScheduleWrapperList(schedules, medicineNames);
		return response;
	}
	
	@Override
	public List<ScheduleWrapper> getScheduleByMrId(Integer mrId) throws ResourceNotFoundException {
		List<Schedule> schedules = scRepo.findAllByMrMrId(mrId);
		
		if(schedules == null || schedules.isEmpty()) {
			throw new ResourceNotFoundException("Medical Representative Id", mrId);
		}
		
		Map<String, String> medicineNames = 
				ScheduleUtil.extractMedicineNamesFromSchedule(schedules, medFeignClient);
		List<ScheduleWrapper> response = 
				ScheduleUtil.convertScheduleListToScheduleWrapperList(schedules, medicineNames);
		return response;
	}

}
