package com.vitacure.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vitacure.authservice.model.Pharmacy;


@FeignClient("PHARMACY-SERVICE")
public interface PharmacyAuthClient {
	@PostMapping("/pharmacy/addPharmacyByAuth")
	public ResponseEntity<?> addPharmacyByAuth(@RequestBody Pharmacy pharmacy);
}
