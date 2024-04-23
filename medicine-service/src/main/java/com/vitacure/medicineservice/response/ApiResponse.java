/**
 * Represents an API response containing information about the success status and a message.
 * This class is commonly used to return responses from API endpoints.
 * 
 * @param success Indicates whether the operation was successful (true) or not (false).
 * @param message A descriptive message associated with the response.
 * @param data The desired resource(s).
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.medicineservice.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private boolean success;
	private String message;
	private Object data;
}
