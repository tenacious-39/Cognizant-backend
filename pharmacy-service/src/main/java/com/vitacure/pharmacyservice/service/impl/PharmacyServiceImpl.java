package com.vitacure.pharmacyservice.service.impl;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.vitacure.pharmacyservice.exception.ResourceNotFoundException;
import com.vitacure.pharmacyservice.feign.MedicineFeignClient;
import com.vitacure.pharmacyservice.model.MedicineStockDto;
import com.vitacure.pharmacyservice.model.Order;
import com.vitacure.pharmacyservice.model.OrderWrapper;
import com.vitacure.pharmacyservice.model.Pharmacy;
import com.vitacure.pharmacyservice.model.PharmacyWrapper;
import com.vitacure.pharmacyservice.model.Supply;
//import com.vitacure.pharmacyservice.model.SupplyWrapper;
import com.vitacure.pharmacyservice.repository.OrderRepository;
import com.vitacure.pharmacyservice.repository.PharmacyRepository;
import com.vitacure.pharmacyservice.repository.SupplyRepository;
import com.vitacure.pharmacyservice.response.ApiResponse;
import com.vitacure.pharmacyservice.service.PharmacyService;
import com.vitacure.pharmacyservice.util.OrderUtil;
import com.vitacure.pharmacyservice.util.PharmacyUtil;
//import com.vitacure.pharmacyservice.util.SupplyUtil;

/**
 * @author Kritidipta Ghosh
 */

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmaRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private SupplyRepository supplyRepo;
	
	@Autowired
	private MedicineFeignClient medClient;
	
	/**
	 * Returns all the pharmacies from the table
	 * 
	 * @return a list of {@literal PharmacyWrapper}
	 */
	@Override
	public List<PharmacyWrapper> getAllPharmacies() {
		List<Pharmacy> responseList = pharmaRepo.findAll();
		List<PharmacyWrapper> pharmacies = 
				PharmacyUtil.convertPharmacyListToPharmacyWrapperList(responseList);
		return pharmacies;
	}
	
	/**
	 * Returns the pharmacy details based on the pharmacyId.
	 * 
	 * @param pharmaId must not be {@literal null}
	 * @return the pharmacy details(PharmacyWrapper) of type with the given id. 
	 * If the id is not found
	 *  then {@literal null} is returned.
	 */
	@Override
	public PharmacyWrapper getPharmacyById(String pharmaId) throws ResourceNotFoundException {
		Pharmacy response = pharmaRepo.findById(pharmaId).orElse(null);
		if(response == null) {
			throw new ResourceNotFoundException("pharmacyId", pharmaId);
		}
		PharmacyWrapper pharmacy = 
				PharmacyUtil.convertPharmacyToPharmacyWrapper(response);
		return pharmacy;
	}
	
	/**
	 * Create new order in the database.
	 * @param order must not be {@literal null}
	 * @return {@literal true} if the order has been successfully created and 
	 * 			{@literal false} if order has not been created.
	 */
	@Override
	public boolean placeOrder(Order order) {
		orderRepo.save(order);
		return true;
	}
	
	
	/**
	 * Insert new pharmacy details in the database and returns the same
	 * after insertion.
	 * 
	 * @param pharmacy must not be {@literal null}
	 * @return {@literal true} if the insertion is complete, 
	 * 			otherwise {@literal false}
	 */
	@Override
	public PharmacyWrapper addPharmacy(Pharmacy pharmacy) {
		Pharmacy response = pharmaRepo.save(pharmacy);
		PharmacyWrapper savedPharmacy = 
				PharmacyUtil.convertPharmacyToPharmacyWrapper(response);
		return savedPharmacy;
	}
	
	/**
	 * Returns all orders with specific information in the {@literal OrderWrapper} format.
	 * 
	 * @return a list of type {@literal OrderWrapper}.
	 */
	@Override
	public List<OrderWrapper> getAllOrders() {
		List<Order> responseList = orderRepo.findAll();
		List<OrderWrapper> orderInfo = 
				OrderUtil.convertOrderListToOrderWrapperList(responseList);
		return orderInfo;
	}
	
	/**
	 * Retrieves all the orders in the {@literal OrderWrapper}
	 * format based on the parameter pharmaId
	 * 
	 * @param pharmaId must not be {@literal null}
	 * @return list of {@literal OrderWrapper}
	 */
	@Override
	public List<OrderWrapper> getAllOrdersByPharmaId(String pharmaId) throws ResourceNotFoundException {
		List<Order> responseList = orderRepo.findAllByPharmacyPharmaId(pharmaId);
		if(responseList == null || responseList.isEmpty()) {
			throw new ResourceNotFoundException("pharmacyId", pharmaId);
		}
		List<OrderWrapper> orderList = 
				OrderUtil.convertOrderListToOrderWrapperList(responseList);
		return orderList;
	}
	
	/**
	 * Updates the pharmacy in the database.
	 *
	 * @param pharmacy must not be {@literal null}
	 * @return true if the pharmacy details is updated successfully.
	 * 			false if the pharmacy is not found.
	 */
	@Override
	public PharmacyWrapper updatePharmacy(Pharmacy pharmacy) throws ResourceNotFoundException {
		List<Pharmacy> pharmacies = pharmaRepo.findAll();
		Pharmacy pharmaObj = null;
		for(Pharmacy p : pharmacies) {
			if(p.getPharmaId().equals(pharmacy.getPharmaId())) {
				p.setName(pharmacy.getName());
				p.setLocation(pharmacy.getLocation());
				pharmaObj = pharmaRepo.save(p);
				break;
			}
		}
		if(pharmaObj == null) {
			throw new ResourceNotFoundException("pharmacyId", pharmacy.getPharmaId());
		}
		return PharmacyUtil.convertPharmacyToPharmacyWrapper(pharmaObj);
	}
	
	
	/**
	 * Checks the medicine stock from MEDICINE-SERVICE
	 * and allot medicine accordingly.
	 * 
	 * @param supply must not be {@literal null}
	 * @return true if medicine allocation is successful
	 * 		   false otherwise.
	 */
	@Override
	public boolean addSupply(Supply supply) {
		MedicineStockDto medDto = new MedicineStockDto(
				supply.getMedicineName(),
				supply.getQuantity()
			);
		ApiResponse response = medClient.updateStock(medDto).getBody();
		if(response.isSuccess()) {
			Supply obj = supplyRepo.save(supply);
			return obj != null;
		}

		return false;
	}
	
	@Override
	public PharmacyWrapper getPharmacyByEmail(String email) throws ResourceNotFoundException {
		Optional<Pharmacy> pharma = pharmaRepo.findByEmail(email);
		if(pharma.isPresent() == false) {
			throw new ResourceNotFoundException("email", email);
		}
		
		return PharmacyUtil.convertPharmacyToPharmacyWrapper(pharma.get());
	}
	
}
