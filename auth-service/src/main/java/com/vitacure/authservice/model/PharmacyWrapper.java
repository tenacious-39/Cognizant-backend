package com.vitacure.authservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kritidipta Ghosh
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class PharmacyWrapper {
	private String pharmaId;
	private String name;
	private String email;
	private String location;
}
