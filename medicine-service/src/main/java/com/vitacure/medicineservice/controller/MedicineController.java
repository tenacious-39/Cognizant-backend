/**
 * Represents a REST controller for managing medicine data.
 * This controller handles requests related to medicine information.
 * <p>
 * Use this class to create RESTful endpoints for retrieving medicine details.
 * </p>
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.medicineservice.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitacure.medicineservice.model.Medicine;
import com.vitacure.medicineservice.model.MedicineStockDto;
import com.vitacure.medicineservice.response.ApiResponse;
import com.vitacure.medicineservice.service.MedicineService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/medicine")
//@CrossOrigin("*")
public class MedicineController {
	
	@Autowired
	private MedicineService medService;

	/**
	 * Checks health of the medicine service.
	 * 
	 * @return {@link String} response 
	 * 			i.e. "Medicine-service health: OK";
	 */
	@GetMapping("/health")
	public String health() {
		return "Medicine-service health: OK";
	}
	
	/**
	 * Retrieves {@link Medicine} entity based on medId.
	 * 
	 * @param medId - The unique identifier of a medicine.
	 * @return {@link ResponseEntity} containing {@link Medicine} object containing medicine details.
	 */
	@GetMapping("/{medId}")
	public ResponseEntity<?> getMedicineInfo(@PathVariable String medId) { 
		Medicine response = medService.getMedicineById(medId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Medicine details", response),
				HttpStatus.OK
		);
	}
	
	/**
	 *  Retrieves all {@link Medicine} entities from the database.
	 *  
	 * @return {@link ResponseEntity} containing list of {@link Medicine} objects;
	 */
	@GetMapping("/allMedicines")
	public ResponseEntity<?> getAllMedicines(){
		List<Medicine> response = medService.getAllMedicines();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "All medicine list", response),
				HttpStatus.OK
			);
	}
	
    /**
     * Retrieves a list of medicine names related to the specified ailment.
     *
     * @param targetAilment The ailment for which medicine names are requested.
     * @return A {@link ResponseEntity} containing either a list of medicine names (if found)
     *         or an error response (if no medicine is found).
     */
	@GetMapping("/byAilment/{targetAilment}")
	public ResponseEntity<?> getMedicineByAilment(@PathVariable String targetAilment){
		List<String> response = medService.getMedicineByAilment(targetAilment);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Medicines by target ailment", response),
				HttpStatus.OK
			);
	}
	
	/**
	 * Updates the stock of a medicine.
	 *
	 * This method handles the stock update for a specific medicine based on the provided
	 * {@link MedicineStockDto}. It validates the input data and ensures that the stock
	 * information is correctly updated.
	 *
	 * @param medDto The {@link MedicineStockDto} containing the medicine name and
	 *               quantity.
	 * @return A {@link ResponseEntity} with an {@link ApiResponse} indicating the
	 *         success of the stock update.
	 *         - If successful, the response will have a status code of 200 (OK) and a
	 *           message: "Successfully updated the stock".
	 *         - If there's an error during the update process, appropriate error
	 *           responses will be returned.
	 *
	 * @see MedicineStockDto
	 * @see ApiResponse
	 */
	@PutMapping("/updateStock")
	public ResponseEntity<?> updateStock(@Valid @RequestBody MedicineStockDto medDto, 
			@RequestHeader(name = "Authorization") String token){
		medService.updateStock(medDto);
		
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Successfully updated the stock", null),
				HttpStatus.OK
				);
	}
	
	
	/**
	 * Inserts a new medicine into the system.
	 *
	 * This method handles the addition of a new medicine based on the provided
	 * {@link Medicine} object. It validates the input data and ensures that the
	 * medicine information is correctly added or updated.
	 *
	 * @param med The {@link Medicine} object representing the medicine details.
	 * @return A {@link ResponseEntity} with an {@link ApiResponse} indicating the
	 *         success of adding the new medicine.
	 *         - If successful, the response will have a status code of 201 (Created)
	 *           and a message: "New medicine added".
	 *         - If there's an error during the addition process, appropriate error
	 *           responses will be returned.
	 *
	 * @see Medicine
	 * @see ApiResponse
	 */
	@PostMapping("/add")
	public ResponseEntity<?> insertNewMedicine(@Valid @RequestBody Medicine med) {
		Medicine res = medService.addMedicine(med);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "New medicine added", res),
				HttpStatus.CREATED
		);
		
	}
	
	
	/**
	 * Updates medicine into the system based on medicine id.
	 *
	 * This method handles the updation of an existing medicine based on the provided
	 * {@link Medicine} object. It validates the input data and ensures that the
	 * medicine information is correctly updated.
	 *
	 * @param med The {@link Medicine} object representing the medicine details.
	 * @return A {@link ResponseEntity} with an {@link ApiResponse} indicating the
	 *         success of updating the medicine.
	 *
	 * @see Medicine
	 * @see ApiResponse
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateMedicineInfo(@Valid @RequestBody Medicine med){
		Medicine medicine = medService.updateMedicine(med);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Updated the data successfully.", medicine),
				HttpStatus.OK
				);

	}
	
	
	/**
	 * Deletes a medicine from the database based on its unique identifier.
	 *
	 * This method handles the deletion of a specific medicine identified by the
	 * provided `medId`. It ensures that the medicine is removed from the database.
	 *
	 * @param medId The unique identifier of the medicine to be deleted.
	 * @return A {@link ResponseEntity} with an {@link ApiResponse} indicating the
	 *         success of the deletion.
	 *         - If successful, the response will have a status code of 204 (No
	 *           Content) and a message: "Successfully deleted from the database."
	 *
	 * @see ApiResponse
	 */
	@DeleteMapping("/{medId}")
	public ResponseEntity<?> deleteMedicineById(@PathVariable String medId) {
		medService.deleteMedicineById(medId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true,
						"Successfully deleted from the database.", null),
				HttpStatus.NO_CONTENT
				);
	}
}
