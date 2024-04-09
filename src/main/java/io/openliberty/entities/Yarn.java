package io.openliberty.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

// Stored in MongoDB via Jakarta NoSQL

@jakarta.nosql.Entity
public class Yarn {
	
	@jakarta.nosql.Id
	private long id;
	
	@jakarta.nosql.Column
	private int stock;
	
	@jakarta.nosql.Column
	private long cost;
	
	@jakarta.nosql.Column
	private String brand;
	
	@jakarta.nosql.Column
	private String colorway;
	
	@jakarta.nosql.Column
	@NotNull
	private Fiber primary;
	
	@jakarta.nosql.Column
	@Min(50)
	@Max(100)
	private long primaryPercent;
	
	public Yarn(long id, 
			int stock, long cost, 
			String brand, String colorway, 
			Fiber primary, long primaryPercent) {
		
		super();
		this.id = id;
		this.stock = stock;
		this.cost = cost;
		this.brand = brand;
		this.colorway = colorway;
		this.primary = primary;
		this.primaryPercent = primaryPercent;
	}
	
	public void decrementQuantity(int quantity) {
		this.stock -= quantity;
	}	
}
