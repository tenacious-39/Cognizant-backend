package com.vitacure.doctorservice.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleWrapper {
	private int scheduleId;
	private String ailment;
	private String medicineId;
	private String medicineName;
	private Date date;
	private DoctorWrapper doctor;
	private MrWrapper mr;
}
