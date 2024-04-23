package com.vitacure.doctorservice.service;

import java.util.List;

import com.vitacure.doctorservice.model.Doctor;
import com.vitacure.doctorservice.model.DoctorWrapper;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

public interface DoctorService {
	
	/**
	 * Retrieves the {@link Doctor}(entity) based on the doctorId.
	 * 
	 * @param doctorId must not be {@literal null}
	 * @return {@link DoctorWrapper} object
	 */
	public DoctorWrapper getDoctorById(int doctorId);
	
	/**
	 * Insert new {@link Doctor} in the database
	 * 
	 * @param doctor must not be {@literal null}
	 * @return {@link DoctorWrapper} entity after insertion
	 */
	public DoctorWrapper addDoctor(Doctor doctor);
	
	/**
	 * Retrieves all {@link Doctor}(s) from database
	 * 
	 * @return List of type {@link DoctorWrapper}
	 */
	public List<DoctorWrapper> getAllDoctors();
	
	
	/**
	 * Retrieves the {@link Doctor} based on doctorId and if found
	 * updates the details.
	 * 
	 * @param doctor must not be {@literal null}
	 * @return the updated details of the {@link DoctorWrapper} if found,
	 * 			{@literal null} otherwise
	 */
	public DoctorWrapper updateDoctor(Doctor doctor);
	
	
	/**
	 * Retrieves the {@link Doctor} list based on the specialization
	 * 
	 * @param specs must not be {@literal null}
	 * @return the list of {@link DoctorWrapper}(s)
	 */
	public List<DoctorWrapper> getAllDoctorsBySpecialization(String specs);
}
