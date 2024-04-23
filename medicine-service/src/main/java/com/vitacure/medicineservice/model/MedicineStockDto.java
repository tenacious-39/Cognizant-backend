/**
 * Represents a data transfer object (DTO) for medicine stock information.
 * This class encapsulates details about a specific medicine, including its name and quantity.
 * <p>
 * Use this DTO to transfer medicine stock data between different layers of the application.
 * </p>
 *
 * @author Kritidipta Ghosh
 * @version 1.0
 */

package com.vitacure.medicineservice.model;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@Scope("prototype")
@NoArgsConstructor
public class MedicineStockDto {
	private String name;
	private int quantity;
}
