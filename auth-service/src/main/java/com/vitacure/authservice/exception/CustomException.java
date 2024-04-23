package com.vitacure.authservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CustomException(String msg) {
		super(msg);
	}
}
