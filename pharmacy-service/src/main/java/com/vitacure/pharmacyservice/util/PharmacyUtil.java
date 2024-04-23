package com.vitacure.pharmacyservice.util;

import java.util.ArrayList;
import java.util.List;

import com.vitacure.pharmacyservice.model.Pharmacy;
import com.vitacure.pharmacyservice.model.PharmacyWrapper;

public class PharmacyUtil {
	
	/**
	 * Converts {@literal List<Pharmacy>} to {@literal List<PharmacyWrapper>}
	 * 
	 * @param responseList
	 * @return list of PharmacyWrapper
	 */
	public static List<PharmacyWrapper> convertPharmacyListToPharmacyWrapperList(List<Pharmacy> responseList){
		List<PharmacyWrapper> pharmacies = new ArrayList<>();
		for(Pharmacy p : responseList) {
			pharmacies.add(
					new PharmacyWrapper(
						p.getPharmaId(),
						p.getName(),
						p.getEmail(),
						p.getLocation()
					)
			);
		}
		return pharmacies;
	}
	
	/**
	 * Converts Pharmacy object to PharmacyWrapper
	 * @param pharmacy of type Pharmacy
	 * @return a PharmacyWrapper object
	 */
	public static PharmacyWrapper convertPharmacyToPharmacyWrapper(Pharmacy pharmacy) {
		if(pharmacy == null) return null;
		PharmacyWrapper pharmacyWrapper = new PharmacyWrapper(
				pharmacy.getPharmaId(),
				pharmacy.getName(),
				pharmacy.getEmail(),
				pharmacy.getLocation()
			);
		return pharmacyWrapper;
	}
}
