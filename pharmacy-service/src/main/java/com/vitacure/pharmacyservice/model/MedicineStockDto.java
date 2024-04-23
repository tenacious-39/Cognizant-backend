package com.vitacure.pharmacyservice.model;

/**
 * @author Kritidipta Ghosh
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
public class MedicineStockDto {
	private String name;
	private int quantity;
}

