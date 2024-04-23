package com.vitacure.authservice.model;

import java.sql.Timestamp;
//import java.util.Date;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Order {
	
	private int orderId;
	
	private String medicineName;
	private int quantity;
	
	private Timestamp dateOfOrder;
	
	private Pharmacy pharmacy;
	
	private Supply supply;
}
