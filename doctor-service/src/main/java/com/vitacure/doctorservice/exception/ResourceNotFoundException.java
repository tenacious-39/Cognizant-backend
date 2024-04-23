/**
 * Custom exception class for handling resource not found scenarios.
 * This exception is thrown when a doctor or MR with a specific field value
 * (e.g., ID, name, etc.) is not found in the system.
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.doctorservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String fieldValue;
	private int fieldValueInt;
	
	public ResourceNotFoundException(String fieldName, String fieldValue) {
		super(String.format("No data found with %s: %s", fieldName, fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String fieldName, int fieldValueInt) {
		super(String.format("No data found with %s: %s", fieldName, fieldValueInt));
		this.fieldName = fieldName;
		this.fieldValueInt = fieldValueInt;
	}

}
