package com.vitacure.doctorservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParseDateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String givenDateStr;
	
	public ParseDateException(String date) {
		super("The given date format cannot be parsed: "+date);
		this.givenDateStr = date;
	}
}
