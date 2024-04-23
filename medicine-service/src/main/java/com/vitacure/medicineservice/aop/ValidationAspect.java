package com.vitacure.medicineservice.aop;

import java.util.LinkedHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vitacure.medicineservice.exception.CustomException;
import com.vitacure.medicineservice.exception.InvalidJwtToken;
import com.vitacure.medicineservice.feign.AuthFeignClient;
import com.vitacure.medicineservice.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ValidationAspect {
	
	@Autowired
	private AuthFeignClient authFeignClient;
	
	@SuppressWarnings("unchecked")
	@Before("execution(* com.vitacure.medicineservice.controller.MedicineController.updateStock(..)) || execution(* com.vitacure.medicineservice.controller.MedicineController.insertNewMedicine(..)) ||execution(* com.vitacure.medicineservice.controller.MedicineController.updateMedicineInfo(..)) || execution(* com.vitacure.medicineservice.controller.MedicineController.deleteMedicineById(..))")
	public void validateUserWithAdminRole() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    String token = request.getHeader("Authorization");

	    ResponseEntity<ApiResponse> response = authFeignClient.validateToken(token);
	    
	    if(response.getBody().isSuccess() == false) {
	    	throw new InvalidJwtToken();
	    }
	    LinkedHashMap<String, String> props = (LinkedHashMap<String, String>)response.getBody().getData();
	    String role = props.get("role");
	    if(role.equals("ROLE_ADMIN") == false) {
	    	throw new CustomException("You are not authorised to access the resource");
	    }
	}
}
