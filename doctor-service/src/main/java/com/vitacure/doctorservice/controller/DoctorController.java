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

import com.vitacure.doctorservice.model.Doctor;
import com.vitacure.doctorservice.model.DoctorWrapper;
import com.vitacure.doctorservice.service.DoctorService;

import jakarta.validation.Valid;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@RestController
@RequestMapping("/doctor")
//@CrossOrigin("*")
public class DoctorController {
	
	@Autowired
	DoctorService docService;
	
	@GetMapping("/health")
	public String checkHealth() {
		return "OK";
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable int doctorId){
		return new ResponseEntity<DoctorWrapper>(
				docService.getDoctorById(doctorId),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/allDoctors")
	public ResponseEntity<?> getAllDoctors(){
		return new ResponseEntity<List<DoctorWrapper>>(
				docService.getAllDoctors(),
				HttpStatus.OK
				);
	}
	
	@GetMapping("/byAilment/{ailment}")
	public ResponseEntity<?> getAllDoctorsBySpecialization(@PathVariable String ailment){
		return new ResponseEntity<List<DoctorWrapper>>(
				docService.getAllDoctorsBySpecialization(ailment),
				HttpStatus.OK
				);
	}
	
	@PostMapping("/addDoctor")
	public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctor doctor){
		return new ResponseEntity<DoctorWrapper>(
				docService.addDoctor(doctor),
				HttpStatus.CREATED
				);
	}
	
	@PostMapping("/addDoctorByAuth")
	public ResponseEntity<?> addDoctorByAuth(@RequestBody Doctor doctor){
		return new ResponseEntity<DoctorWrapper>(
				docService.addDoctor(doctor),
				HttpStatus.CREATED
				);
	}
	
	@PutMapping("/updateDoctor")
	public ResponseEntity<?> updateDoctor(@Valid @RequestBody Doctor doctor){
		return new ResponseEntity<DoctorWrapper>(
				docService.updateDoctor(doctor),
				HttpStatus.OK
				);
	}
}
