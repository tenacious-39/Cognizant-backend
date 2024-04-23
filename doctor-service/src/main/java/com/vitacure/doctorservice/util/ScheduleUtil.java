package com.vitacure.doctorservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vitacure.doctorservice.feign.MedicineFeignClient;
import com.vitacure.doctorservice.model.Medicine;
import com.vitacure.doctorservice.model.Schedule;
import com.vitacure.doctorservice.model.ScheduleWrapper;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

public class ScheduleUtil {
	
	public static ScheduleWrapper convertScheduleToScheduleWrapper(Schedule s, String medicineName) {
		return new ScheduleWrapper(
				s.getScheduleId(),
				s.getAilment(),
				s.getMedicineId(),
				medicineName,
				s.getDate(),
				DoctorUtil.convertDoctorToDoctorWrapper(s.getDoctor()),
				MrUtil.convertMedicalRepresentativeToMrWrapper(s.getMr())
			);
	}
	
	public static List<ScheduleWrapper> convertScheduleListToScheduleWrapperList(
			List<Schedule> schedules, Map<String, String> medicineNames){
		List<ScheduleWrapper> newList = new ArrayList<>();
		for(Schedule s : schedules) {
			newList.add(
					convertScheduleToScheduleWrapper(s, medicineNames.get(s.getMedicineId()))
			);
		}
		return newList;
	}
	
	public static Map<String, String> extractMedicineNamesFromSchedule(
		List<Schedule> schedules, MedicineFeignClient medFeignClient){
		Map<String, String> medicineNames = new HashMap<>();
		for(Schedule schedule : schedules) {
			Medicine m = medFeignClient.getMedicineInfo(schedule.getMedicineId()).getBody();
			if(m != null) medicineNames.put(schedule.getMedicineId(), m.getName());
		}
		return medicineNames;
	}
}
