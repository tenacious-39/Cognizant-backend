package com.vitacure.pharmacyservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Scope("prototype")
public class Pharmacy {
	@Id
	private String pharmaId;
	
	@NotBlank(message = "Pharmacy name should not be blank")
	private String name;
	
	@NotBlank(message = "Pharmacy email should not be blank")
	private String email;
	
	@NotBlank(message = "Pharmacy location should not be blank")
	private String location;
	
	@OneToMany(mappedBy = "pharmacy")
	private List<Order> orders = new ArrayList<>();
}
