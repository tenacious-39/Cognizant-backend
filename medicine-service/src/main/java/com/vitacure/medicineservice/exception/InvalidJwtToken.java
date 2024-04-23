package com.vitacure.medicineservice.exception;


public class InvalidJwtToken extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidJwtToken() {
		super("Invalid / Expired Jwt Token");
	}
}
