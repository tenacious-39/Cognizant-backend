package com.vitacure.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vitacure.authservice.model.Doctor;


@FeignClient("DOCTOR-SERVICE")
public interface DoctorAuthClient {
	@PostMapping("/addDoctorByAuth")
	public ResponseEntity<?> addDoctorByAuth(@RequestBody Doctor doctor);
}
