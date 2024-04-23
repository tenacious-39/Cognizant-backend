package com.vitacure.medicineservice.exception;

public class InsufficientMedicineStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientMedicineStockException() {
		super("Medicine stock is insufficient");
	}
}
