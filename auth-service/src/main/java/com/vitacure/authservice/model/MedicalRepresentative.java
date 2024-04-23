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
public class MedicalRepresentative {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mrId;
	
	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@Size(min = 10, max = 10)
	private String phoneNumber;
	
	@NotBlank(message = "Name should not be empty")
	private String email;
	private String workLocation;
	
	@OneToMany(mappedBy = "mr")
	List<Schedule> schedules;
}
