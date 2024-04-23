package com.vitacure.doctorservice.aop;

import java.util.LinkedHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vitacure.doctorservice.exception.CustomException;
import com.vitacure.doctorservice.exception.InvalidJwtToken;
import com.vitacure.doctorservice.feign.AuthFeignClient;
import com.vitacure.doctorservice.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
@SuppressWarnings("unchecked")
public class ValidationAspect {
	
	@Autowired
	private AuthFeignClient authFeignClient;
	
	@Before("execution(* com.vitacure.doctorservice.controller.DoctorController.addDoctor(..)) || execution(* com.vitacure.doctorservice.controller.DoctorController.updateDoctor(..))")
	public void validateUserWithDoctorRole() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    String token = request.getHeader("Authorization");

	    ResponseEntity<ApiResponse> response = authFeignClient.validateToken(token);
	    
	    if(response.getBody().isSuccess() == false) {
	    	throw new InvalidJwtToken();
	    }
	    LinkedHashMap<String, String> props = (LinkedHashMap<String, String>)response.getBody().getData();
	    String role = props.get("role");
	    if(role.equals("ROLE_ADMIN") == false && 
    		role.equals("ROLE_DOCTOR") == false && 
    		role.equals("ROLE_MR") == false) {
	    	throw new CustomException("You are not authorised to access the resource");
	    }
	}
	
	@Before("execution(* com.vitacure.doctorservice.controller.MrController.createMr(..)) || execution(* com.vitacure.doctorservice.controller.MrController.updateMr(..)) || execution(* com.vitacure.doctorservice.controller.ScheduleController.addSchedule(..))")
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
    		role.equals("ROLE_MR") == false) {
	    	throw new CustomException("You are not authorised to access the resource");
	    }
	}
}
