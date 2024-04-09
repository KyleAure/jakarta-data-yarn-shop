package io.openliberty.yarn.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import io.openliberty.entities.PurchaseOrder;
import io.openliberty.shop.Purchase;

@MicroShedTest
@SharedContainerConfig(AppContainerConfig.class)
public class PurchaseTestIT {
	
	@RESTClient
	public static Purchase purchaseService;

	@Test
	public void testGetAllOrders() {
		Collection<PurchaseOrder> orders = purchaseService.getAllOrders();
		assertEquals(0, orders.size());
	}
}
