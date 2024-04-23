/**
 * Controller class for managing pharmacy-related endpoints.
 * Provides RESTful APIs for inserting, retrieving pharmacies,
 * getting pharmacy details by ID, allotting medicines and placing orders etc.
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */


package com.vitacure.pharmacyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitacure.pharmacyservice.model.Order;
import com.vitacure.pharmacyservice.model.OrderWrapper;
import com.vitacure.pharmacyservice.model.Pharmacy;
import com.vitacure.pharmacyservice.model.PharmacyWrapper;
import com.vitacure.pharmacyservice.model.Supply;
//import com.vitacure.pharmacyservice.model.SupplyWrapper;
import com.vitacure.pharmacyservice.response.ApiResponse;
import com.vitacure.pharmacyservice.service.PharmacyService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/pharmacy")
//@CrossOrigin("*")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmaService;
	
	
	/**
	 * Checks health of the pharmacy service.
	 * 
	 * @return {@link String} response 
	 * 			i.e. "OK";
	 */
	@GetMapping("/health")
	public String checkHealth() {
		return "OK";
	}
	
	
	@GetMapping("/allPharmacies")
	public ResponseEntity<?> getAllPharmacies(){
		List<PharmacyWrapper> allPharmas = pharmaService.getAllPharmacies();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Pharmacy list", allPharmas),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/{pharmaId}")
	public ResponseEntity<?> getPharmacyById(@PathVariable String pharmaId){
		PharmacyWrapper response = pharmaService.getPharmacyById(pharmaId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Pharmacy details", response),
				HttpStatus.OK
		);
		
	}
	
	@PostMapping("/place-order")
	public ResponseEntity<?> placeOrder(@Valid @RequestBody Order order){	
		pharmaService.placeOrder(order);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Order placed succesfully", null),
				HttpStatus.OK);
	}
	
	@PostMapping("/addPharmacy")
	public ResponseEntity<?> addPharmacy(@Valid @RequestBody Pharmacy pharmacy){
			PharmacyWrapper response = pharmaService.addPharmacy(pharmacy);			
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true,
							"Pharmacy details added successfully", response),
					HttpStatus.CREATED
			);
	}
	
	@PostMapping("/addPharmacyByAuth")
	public ResponseEntity<?> addPharmacyByAuth(@RequestBody Pharmacy pharmacy){
			PharmacyWrapper response = pharmaService.addPharmacy(pharmacy);		
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true,
							"Pharmacy details added successfully", response),
					HttpStatus.CREATED
			);
	}
	
	@PutMapping("/updatePharmacy")
	public ResponseEntity<?> updatePharmacy(@Valid @RequestBody Pharmacy pharmacy){
		PharmacyWrapper response = pharmaService.updatePharmacy(pharmacy);
		
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Pharmacy updated successfully", response),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<?> getAllOrders(){
		List<OrderWrapper> responseList = pharmaService.getAllOrders();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Order list", responseList),
				HttpStatus.OK);
	}
	
	@GetMapping("/allOrders/{pharmaId}")
	public ResponseEntity<?> getAllOrdersByPharmaId(@PathVariable String pharmaId){
		List<OrderWrapper> orders = pharmaService.getAllOrdersByPharmaId(pharmaId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Order list for the pharmacy", orders),
				HttpStatus.OK
		);
	}
	
	
	@PostMapping("/allot-medicine")
	public ResponseEntity<?> addSupply(@Valid @RequestBody Supply supply){
		pharmaService.addSupply(supply);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Successfully alloted medicine", null), 
				HttpStatus.OK
			);
	}
	
	@GetMapping("/byEmail/{email}")
	public ResponseEntity<?> getPharmacyByEmail(@PathVariable String email){
		PharmacyWrapper response = pharmaService.getPharmacyByEmail(email);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "Pharmacy Details", response), 
				HttpStatus.OK
			);
	}

}
