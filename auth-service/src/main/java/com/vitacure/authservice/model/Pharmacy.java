package com.vitacure.authservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Pharmacy {
	private String pharmaId;
	
	private String name;
	
	private String email;
	
	private String location;
	
	private List<Order> orders = new ArrayList<>();
}
