package com.vitacure.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vitacure.authservice.dto.LoginResponse;
import com.vitacure.authservice.dto.SignupResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * Handles the {@link ResourceNotFoundException} and returns an HTTP response
     * with a status code of 404 (Not Found).
     *
     * @param ex The exception instance.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with
     *         an error message and a status code of 404.
     */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<LoginResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<LoginResponse>(
				new LoginResponse(false, null, message),
				HttpStatus.NOT_FOUND
		);
	}
	
	@ExceptionHandler(InvalidJwtToken.class)
	public ResponseEntity<LoginResponse> invalidJwtTokenHandler(InvalidJwtToken ex){
		String message = ex.getMessage();
		return new ResponseEntity<LoginResponse>(
				new LoginResponse(false, null, message),
				HttpStatus.UNAUTHORIZED
		);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<SignupResponse> customExceptionHandler(CustomException ex){
		String message = ex.getMessage();
		return new ResponseEntity<SignupResponse>(
				new SignupResponse(false, message, null),
				HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<SignupResponse> generalExceptionHandler(Exception ex){
		String message = ex.getMessage();
		return new ResponseEntity<SignupResponse>(
				new SignupResponse(false, message, null),
				HttpStatus.BAD_GATEWAY
		);
	}
}
