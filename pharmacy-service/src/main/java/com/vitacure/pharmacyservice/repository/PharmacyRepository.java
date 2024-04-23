package com.vitacure.pharmacyservice.repository;

/**
 * @author Kritidipta Ghosh
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.vitacure.pharmacyservice.model.Pharmacy;


@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, String>{
	
	public Optional<Pharmacy> findByEmail(String email);
}
