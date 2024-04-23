package com.vitacure.doctorservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitacure.doctorservice.model.Schedule;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	
	public List<Schedule> findAllByDoctorDoctorId(int doctorId);

	public List<Schedule> findAllByDate(Date date);

	public List<Schedule> findAllByMrMrId(Integer mrId);

}
