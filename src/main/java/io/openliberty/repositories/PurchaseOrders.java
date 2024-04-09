package io.openliberty.repositories;

import java.util.UUID;

import io.openliberty.entities.PurchaseOrder;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface PurchaseOrders extends CrudRepository<PurchaseOrder, UUID> {

}
