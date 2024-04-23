package com.vitacure.doctorservice.service;

import java.util.List;

import com.vitacure.doctorservice.model.MedicalRepresentative;
import com.vitacure.doctorservice.model.MrWrapper;

import jakarta.validation.Valid;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

public interface MrService {
	
	/**
	 * Insert new entity({@link MedicalRepresentative}) to the database
	 * 
	 * @param mr must not be {@literal null}
	 * @return the {@link MrWrapper} after insertion
	 */
	public MrWrapper createMr(MedicalRepresentative mr);
	
	/**
	 * Retrieves the entity({@link MedicalRepresentative}) based on Id
	 * 
	 * @param id must not be {@literal null}
	 * @return the {@link MrWrapper} entity
	 */
	public MrWrapper getMrById(int id);
	
	/**
	 * Retrieves the entity({@link MedicalRepresentative}) based on email
	 * 
	 * @param email must not be {@literal null}
	 * @return the {@link MrWrapper} entity
	 */
	public MrWrapper getMrByEmail(String email);
	
	/**
	 * Retrieves all {@link MedicalRepresentative}(s) from database
	 * 
	 * @return a list of {@link MrWrapper}
	 */
	public List<MrWrapper> getAllMrs();
	
	/**
	 * Updates existing {@link MedicalRepresentative} based on the id
	 * 
	 * @param mr must not be {@literal null}
	 * @return {@link MrWrapper} entity after update
	 */
	public MrWrapper updateMr(@Valid MedicalRepresentative mr);
	
	/**
	 * Retrieves all {@link MedicalRepresentative}(s) from database
	 * based on the work location.
	 * @param workLocation must
	 * @return list of {@link MrWrapper}
	 */
	public List<MrWrapper> getAllMrsByLocation(String workLocation);

}
