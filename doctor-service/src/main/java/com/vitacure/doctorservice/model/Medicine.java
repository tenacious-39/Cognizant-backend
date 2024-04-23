package com.vitacure.doctorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
	private String medicineId;
	private String name;
	private String composition;
	private String targetAilment;
	private int stockAmount;
	private String description;
}
