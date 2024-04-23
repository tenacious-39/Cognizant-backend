package com.vitacure.authservice.model;

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
public class Supply {
	private int supplyId;
	
	private String medicineName;
	private int quantity;
	private String date;
	
	private Order order;
}
