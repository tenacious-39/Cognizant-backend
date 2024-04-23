package com.vitacure.pharmacyservice.model;

import java.sql.Timestamp;
//import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Scope;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "Orders")
@Scope("prototype")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int orderId;
	
	@NotBlank(message = "Medicine name should not be blank")
	private String medicineName;
	private int quantity;
	
	@CreationTimestamp
	private Timestamp dateOfOrder;
	
	@ManyToOne
	private Pharmacy pharmacy;
	
	@OneToOne(mappedBy = "order")
	private Supply supply;
}
