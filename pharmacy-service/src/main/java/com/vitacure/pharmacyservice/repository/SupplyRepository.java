package com.vitacure.pharmacyservice.repository;

//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.vitacure.pharmacyservice.model.Pharmacy;
import com.vitacure.pharmacyservice.model.Supply;


/**
 * @author Kritidipta Ghosh
 */

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer>{

}
