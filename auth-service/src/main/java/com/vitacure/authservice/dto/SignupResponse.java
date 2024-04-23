package com.vitacure.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
	private boolean success;
	private String message;
	private Object data;
}
