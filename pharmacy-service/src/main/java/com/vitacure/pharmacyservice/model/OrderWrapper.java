package com.vitacure.pharmacyservice.model;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class OrderWrapper {
	
	private int orderId;
	private String medicineName;
	private int quantity;
	private Timestamp dateOfOrder;
	private String pharmacyName;
	private String pharmacyEmail;
	private String pharmacyLocation;
}
