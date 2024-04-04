package io.openliberty.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.openliberty.entities.Item;
import io.openliberty.entities.PurchaseOrder;
import io.openliberty.repositories.PurchaseOrders;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

// Create an order

@Path("/purchase")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Purchase {
	
	@Inject
	private PurchaseOrders orders;
	
	@Inject
	UserTransaction tran;
	
    @PostConstruct
    public void initYarns() {
        System.out.println("Seeding order database with sample data");
        //TODO
    }
	
	@GET
	public Collection<PurchaseOrder> getAllOrders() {
		return orders.findAll().collect(Collectors.toList());
	}
	
	@GET
	@Path("/{orderId}")
	public PurchaseOrder getOrder(@PathParam("orderId") UUID id) {
		return orders.findById(id).orElse(null);
	}
	
	@POST
	public PurchaseOrder createOrder(
			@QueryParam("orderId") UUID id,
			@QueryParam("username") String username,
			@QueryParam("items") List<String> itemString
			) {
		
		//If order already exists - return it
		PurchaseOrder order = orders.findById(id).orElse(null);
		if(order != null) {
			return order;
		}
		
		order = new PurchaseOrder(id, username, LocalDateTime.now(), parseItemString(itemString), 1);
		return orders.save(order);
	}
	
	@POST
	@Path("/{orderId}")
	public PurchaseOrder updateOrder(
			@PathParam("orderId") UUID id,
			@QueryParam("items") List<String> itemString) throws Exception {
		
		PurchaseOrder result = null;
		
		tran.begin();
		try {
			PurchaseOrder order = orders.findById(id).orElse(null);			
			order.updateItems(parseItemString(itemString));
			
			result = orders.save(order);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}
		
		return result;
	}
	
	@DELETE
	@Path("/{orderId}")
	public void removeOrder(@QueryParam("orderId") UUID id) {
		orders.deleteById(id);
	}
	
	
	private List<Item> parseItemString(List<String> itemString) {
		List<Item> items = new ArrayList<>();
		Iterator<String> it = itemString.iterator();
		
		while(it.hasNext()) {
			items.add(new Item(Long.parseLong(it.next()), Integer.parseInt(it.next())));
		}
		
		return items;
	}
}
