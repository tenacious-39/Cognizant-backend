package com.vitacure.pharmacyservice.model;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Scope("prototype")
public class Supply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int supplyId;
	
	@NotBlank(message = "Medicine name should not be blank")
	private String medicineName;
	private int quantity;
	private String date;
	
	@OneToOne
	private Order order;
}
