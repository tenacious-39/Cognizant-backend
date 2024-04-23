package com.vitacure.doctorservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vitacure.doctorservice.model.Medicine;

@FeignClient("MEDICINE-SERVICE")
public interface MedicineFeignClient {
	
	@GetMapping("/medicine/{medId}")
	public ResponseEntity<Medicine> getMedicineInfo(@PathVariable String medId);
}
