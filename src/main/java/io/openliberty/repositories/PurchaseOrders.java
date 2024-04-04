package io.openliberty.repositories;

import java.util.UUID;

import io.openliberty.entities.PurchaseOrder;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;

@Repository
public interface PurchaseOrders extends CrudRepository<PurchaseOrder, UUID> {
	@Update
	PurchaseOrder updateOrder(PurchaseOrder order);
}
