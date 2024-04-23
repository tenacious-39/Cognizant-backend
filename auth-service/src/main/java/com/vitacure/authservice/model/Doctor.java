package com.vitacure.authservice.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	
	@NotBlank(message = "Name should not be blank")
	private String name;
	
	@Size(min=10, max=10)
	private String phoneNumber;
	
	@NotBlank(message = "Email should not be blank")
	private String email;
	private String specialization;
	private String clinicLocation;
	
	@OneToMany(mappedBy = "doctor")
	List<Schedule> schedules;
}
