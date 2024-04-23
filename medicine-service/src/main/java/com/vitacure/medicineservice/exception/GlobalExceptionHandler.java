/**
 * Global exception handler for handling exceptions in the medicine service.
 * This class provides a centralized mechanism to handle exceptions 
 * and return an appropriate response to the client.
 * 
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.medicineservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vitacure.medicineservice.response.ApiResponse;

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
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.NOT_FOUND
		);
	}
	
	/**
	 * Handles the {@link InsufficientMedicineStockException} and returns an HTTP response
     * with a status code of 400 (Bad Request).
     *
     * @param ex The exception instance.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with
     *         an error message and a status code of 400.
	 */
	@ExceptionHandler(InsufficientMedicineStockException.class)
	public ResponseEntity<ApiResponse> insufficientMedicineStockExceptionHandler
	(InsufficientMedicineStockException ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.BAD_REQUEST
		);
	}
	
	/**
	 * Handles exceptions thrown by the `MethodArgumentNotValidException` class.
	 *
	 * @param ex The exception to handle.
	 * @return A `ResponseEntity` containing an `ApiResponse` with appropriate details.
	 *         If the exception is related to invalid method arguments, it returns a
	 *         `BAD_REQUEST` status with an error message.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler
	(MethodArgumentNotValidException ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.BAD_REQUEST
		);
	}
	
	
	@ExceptionHandler(InvalidJwtToken.class)
	public ResponseEntity<ApiResponse> invalidJwtTokenExceptionHandler(InvalidJwtToken ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.UNAUTHORIZED
		);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> customExceptionHandler(CustomException ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.UNAUTHORIZED
		);
	}
	
	/**
	 * Handles any unhandled {@link Exception} and returns an HTTP response
     * with a status code of 502 (Bad Gateway).
     * 
	 *  @param ex The exception instance.
     * @return A {@link ResponseEntity} containing an {@link ApiResponse} with
     *         an error message and a status code of 502.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> generalExceptionHandler(Exception ex){
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(false, message, null),
				HttpStatus.BAD_GATEWAY
		);
	}
	
}
