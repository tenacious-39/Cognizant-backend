package com.vitacure.doctorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitacure.doctorservice.model.MedicalRepresentative;
import com.vitacure.doctorservice.model.MrWrapper;
import com.vitacure.doctorservice.service.MrService;

import jakarta.validation.Valid;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@RestController
@RequestMapping("/mr")
//@CrossOrigin("*")
public class MrController {
	
	@Autowired
	MrService mrService;
	
	@GetMapping("/health")
	public String checkHealth() {
		return "OK";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMrById(@PathVariable int id){
		return new ResponseEntity<MrWrapper>(
				mrService.getMrById(id),
				HttpStatus.OK
			);
	}
	
	@GetMapping("/byEmail/{email}")
	public ResponseEntity<?> getMrByEmail(@PathVariable String email){
		return new ResponseEntity<MrWrapper>(
				mrService.getMrByEmail(email),
				HttpStatus.OK
			);
	}
	
	@GetMapping("/allMrs")
	public ResponseEntity<?> getAllMrs(){
		return new ResponseEntity<List<MrWrapper>>(
				mrService.getAllMrs(),
				HttpStatus.OK
			);
	}
	
	@GetMapping("/byLocation/{workLocation}")
	public ResponseEntity<?> getAllMrsByLocation(@PathVariable String workLocation){
		return new ResponseEntity<List<MrWrapper>>(
				mrService.getAllMrsByLocation(workLocation),
				HttpStatus.OK
			);
	}
	
	@PostMapping("/addMr")
	public ResponseEntity<?> createMr(@Valid @RequestBody MedicalRepresentative mr){
		return new ResponseEntity<MrWrapper>(
				mrService.createMr(mr),
				HttpStatus.CREATED
			);
	}
	
	@PutMapping("/updateMr")
	public ResponseEntity<?> updateMr(@Valid @RequestBody MedicalRepresentative mr){
		return new ResponseEntity<MrWrapper>(
				mrService.updateMr(mr),
				HttpStatus.CREATED
			);
	}
	
}
