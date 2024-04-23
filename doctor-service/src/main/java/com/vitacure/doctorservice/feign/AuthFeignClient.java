package com.vitacure.doctorservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vitacure.doctorservice.response.ApiResponse;


@FeignClient("AUTH-SERVICE")
public interface AuthFeignClient {
	
	@GetMapping("/auth/validate/{token}")
	public ResponseEntity<ApiResponse> validateToken(@PathVariable String token);
}
