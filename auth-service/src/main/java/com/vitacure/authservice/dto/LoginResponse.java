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
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private boolean success;
	private String token;
	private String message;
}
