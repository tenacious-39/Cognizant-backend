package com.vitacure.authservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidJwtToken extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidJwtToken() {
		super("Invalid JWT token / Expired JWT token");
	}

}
