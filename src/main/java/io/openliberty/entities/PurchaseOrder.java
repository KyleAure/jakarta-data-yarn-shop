package io.openliberty.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// Stored in PostgreSQL via Jakarta Persistence

@jakarta.persistence.Entity
public class PurchaseOrder {
	
	@jakarta.persistence.Id
	private UUID id;

	private String username;
	private LocalDateTime date;

	private List<Item> items;

	private int versionNum;

	public PurchaseOrder(UUID id, String username, LocalDateTime date, List<Item> items, int versionNum) {
		super();
		this.id = id;
		this.username = username;
		this.date = date;
		this.items = items;
		this.versionNum = versionNum;
	}
	
	public void updateItems(List<Item> items) {
		this.items = items;
		this.versionNum += 1;
	}
}
