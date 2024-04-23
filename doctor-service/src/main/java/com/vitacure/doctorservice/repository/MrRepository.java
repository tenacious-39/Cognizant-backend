package com.vitacure.doctorservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitacure.doctorservice.model.MedicalRepresentative;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Repository
public interface MrRepository extends JpaRepository<MedicalRepresentative, Integer> {
	
	Optional<MedicalRepresentative> findByEmail(String email);

	List<MedicalRepresentative> findAllByWorkLocation(String workLocation);
}
