/**
 * Service implementation for managing medicines.
 * This class provides methods to insert, retrieve, and delete medicine entities.
 * Also provides methods to update the medicine stocks.
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */


package com.vitacure.medicineservice.service.impl;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitacure.medicineservice.exception.InsufficientMedicineStockException;
import com.vitacure.medicineservice.exception.ResourceNotFoundException;
import com.vitacure.medicineservice.model.Medicine;
import com.vitacure.medicineservice.model.MedicineStockDto;
import com.vitacure.medicineservice.repository.MedicineRepository;
import com.vitacure.medicineservice.service.MedicineService;


@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	MedicineRepository medRepo;
	
	/** Inserts new entity(medicine) in the database
	 * 
	 * 	@param medicine must not be {@literal null}
	 *  @return the entity after insertion. Entity will
	 *  never be {@literal null}. 
	 * */
	@Override
	public Medicine addMedicine(Medicine medicine){
		Medicine res = medRepo.save(medicine);
		return res;
	}
	
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
	@Override
	public Medicine updateMedicine(Medicine medicine) throws ResourceNotFoundException {
		Optional<Medicine> res = medRepo.findById(medicine.getMedicineId());
		Medicine medicineObj = null;
		if(res.isPresent()) {
			medicineObj = medRepo.save(medicine);
		} else {
			throw new ResourceNotFoundException("medicineId", medicine.getMedicineId());
		}
		return medicineObj;
	}
	
	/**
	 *  Retrieves the medicine details based on medicine id
	 *   
	 * 	@param medicineId must not be {@literal null}
	 *  @return the entity with the given id. If the id is not found
	 *  then {@literal null} is returned.
	 *  @throws ResourceNotFoundException if {@literal medicineId} is not found
	 */
	@Override
	public Medicine getMedicineById(String medicineId) throws ResourceNotFoundException {
		Optional<Medicine> res = medRepo.findById(medicineId);
		Medicine medicine = res.orElseThrow(
				() -> new ResourceNotFoundException("medicine-id", medicineId));
		return medicine;
	}
	
	/**
	 * Retrieves all the entities(medicines) from the database.
	 * 
	 * @return a list of Medicine.
	 */
	@Override
	public List<Medicine> getAllMedicines(){
		return medRepo.findAll();
	}
	
	/**
	 * Delete the particular medicine from database
	 * based on the medicine id
	 * 
	 * @param medicineId must not be {@literal null}
	 */
	@Override
	public void deleteMedicineById(String medicineId) {
		medRepo.deleteById(medicineId);
	}
	
	/**
	 * Retrieves the list of medicine names based on targetAilment
	 * 
	 * @param targetAilment must not be {@literal null}
	 * @return list of medicine names
	 * @throws ResourceNotFoundException if {@literal targetAilment} is not found
	 */
	@Override
	public List<String> getMedicineByAilment(String targetAilment) throws ResourceNotFoundException {
		List<String> medicineList = medRepo.findByTargetAilment(targetAilment);
		if(medicineList == null || medicineList.isEmpty()) {
			throw new ResourceNotFoundException("Ailment", targetAilment);
		}
		return medicineList;
	}
	
	
	/**
	 * Update the stock of the medicine based on the medicine name.
	 * 
	 * @param medDto must not be {@literal null}
	 * @return {@literal true} for successful update.
	 * @throws ResourceNotFoundException if medDto is not found.
	 * 		   InsufficientMedicineStockException if medDto.quantity > existing stock
	 */
	@Override
	public boolean updateStock(MedicineStockDto medDto) throws ResourceNotFoundException, 
																InsufficientMedicineStockException {
		boolean flag = false;
		List<Medicine> allMedicines = medRepo.findAll();
		for(Medicine m : allMedicines) {
			if(medDto.getName().equalsIgnoreCase(m.getName())) {
				int stockAmount = m.getStockAmount();
				int quantity = medDto.getQuantity();
				if(quantity > stockAmount) {
					throw new InsufficientMedicineStockException();
				}
				m.setStockAmount(stockAmount-quantity);
				medRepo.save(m);
				flag = true;
				break;
			}
		}
		if(flag == false) {
			throw new ResourceNotFoundException("medicine name", medDto.getName());
		}
		return flag;
	}
}
