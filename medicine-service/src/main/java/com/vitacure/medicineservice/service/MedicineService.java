/**
 * Service interface for managing medicines.
 * This interface defines methods to interact with medicine data in the database.
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.medicineservice.service;

import java.util.List;

import com.vitacure.medicineservice.exception.ResourceNotFoundException;
import com.vitacure.medicineservice.model.Medicine;
import com.vitacure.medicineservice.model.MedicineStockDto;

import jakarta.validation.Valid;


public interface MedicineService {
	
	/**
	 * Updates the information of an existing medicine.
	 *
	 * This method handles the update of a specific medicine based on the provided
	 * {@link Medicine} object. It first checks if the medicine with the given ID
	 * exists in the repository. If found, it updates the medicine details and
	 * returns the updated medicine object. If not found, it throws a
	 * {@link ResourceNotFoundException}.
	 *
	 * @param medicine The {@link Medicine} object representing the updated medicine
	 *                 details.
	 * @return The updated {@link Medicine} object.
	 * @throws ResourceNotFoundException If the medicine with the specified ID does
	 *                                  not exist.
	 *
	 * @see Medicine
	 * @see ResourceNotFoundException
	 */
	public Medicine updateMedicine(Medicine medicine);
	
	/**
	 *  Retrieves the {@link Medicine} details based on medicine id
	 *   
	 * 	@param medicineId must not be {@literal null}
	 *  @return the entity with the given id. If the id is not found
	 *  then {@literal null} is returned.
	 */
	public Medicine getMedicineById(String medicineId) throws ResourceNotFoundException;
	
	/**
	 * Retrieves all the entities({@link Medicine}(s)) from the database.
	 * 
	 * @return a list of {@link Medicine}.
	 */
	public List<Medicine> getAllMedicines();
	
	/**
	 * Delete the particular {@link Medicine} from database
	 * based on the medicine id
	 * 
	 * @param medicineId must not be {@literal null}
	 */
	public void deleteMedicineById(String medicineId);
	
	/**
	 * Retrieves the list of {@link Medicine} names based on targetAilment
	 * 
	 * @param targetAilment must not be {@literal null}
	 * @return list of {@link Medicine} names
	 */
	public List<String> getMedicineByAilment(String targetAilment);
	
	/**
	 * Update the stock of the {@link Medicine} based on the medicine name.
	 * 
	 * @param medDto must not be {@literal null}
	 * @return {@literal true} in case of successful update.
	 */
	public boolean updateStock(MedicineStockDto medDto);
	
	/** Inserts new entity(medicine) in the database
	 * 
	 * 	@param medicine must not be {@literal null}
	 *  @return the entity after insertion. Entity will
	 *  never be {@literal null}. 
	 * 
	 */
	public Medicine addMedicine(@Valid Medicine med);
	
	
}
