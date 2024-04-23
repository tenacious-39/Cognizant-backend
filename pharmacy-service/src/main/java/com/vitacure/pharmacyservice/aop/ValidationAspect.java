package com.vitacure.pharmacyservice.aop;

import java.util.LinkedHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vitacure.pharmacyservice.exception.CustomException;
import com.vitacure.pharmacyservice.exception.InvalidJwtToken;
import com.vitacure.pharmacyservice.feign.AuthFeignClient;
import com.vitacure.pharmacyservice.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;


@Component
@Aspect
@SuppressWarnings("unchecked")
public class ValidationAspect {
	
	@Autowired
	private AuthFeignClient authFeignClient;
	
	@Before("execution(* com.vitacure.pharmacyservice.controller.PharmacyController.placeOrder(..)) || execution(* com.vitacure.pharmacyservice.controller.PharmacyController.addPharmacy(..)) || execution(* com.vitacure.pharmacyservice.controller.PharmacyController.updatePharmacy(..))")
	public void validateUserWithMRRole() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    String token = request.getHeader("Authorization");

	    ResponseEntity<ApiResponse> response = authFeignClient.validateToken(token);
	    
	    if(response.getBody().isSuccess() == false) {
	    	throw new InvalidJwtToken();
	    }
	    LinkedHashMap<String, String> props = (LinkedHashMap<String, String>)response.getBody().getData();
	    String role = props.get("role");
	    if(role.equals("ROLE_ADMIN") == false &&  
    		role.equals("ROLE_PHARMACY") == false) {
	    	throw new CustomException("You are not authorised to access the resource");
	    }
	}
}
