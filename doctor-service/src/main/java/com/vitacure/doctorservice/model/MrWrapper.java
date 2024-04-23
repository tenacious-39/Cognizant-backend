package com.vitacure.doctorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MrWrapper {
	private int mrId;
	private String name;
	private String phoneNumber;
	private String email;
	private String workLocation;
}
