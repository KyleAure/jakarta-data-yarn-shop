package io.openliberty.shop;

import java.util.Collection;
import java.util.stream.Collectors;

import io.openliberty.entities.Fiber;
import io.openliberty.entities.Yarn;
import io.openliberty.repositories.Yarns;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

// View / Modify yarn inventory

@Path("/inventory")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Inventory {
	
	@Inject
	private Yarns yarns;
	
    @PostConstruct
    public void initYarns() {
        System.out.println("Seeding inventory database with sample data");
        //TODO
    }
	
	@GET
	public Collection<Yarn> getAllYarns() {
		return yarns.findAll().collect(Collectors.toList());
	}
	
	@GET
	@Path("/{yarnId}")
	public Yarn getOrder(@PathParam("yarnId") long id) {
		return yarns.findById(id).orElse(null);
	}
	
	@POST
	public Yarn createStockItem(
			@QueryParam("yarnId") long id,
			@QueryParam("stock") int stock,
			@QueryParam("cost") long cost,
			@QueryParam("brand") String brand,
			@QueryParam("colorway") String colorway,
			@QueryParam("primary") String primary,
			@QueryParam("primaryPercent") long primaryPercent
			) {
		
		Yarn yarn = yarns.findById(id).orElse(null);
		
		if(yarn != null) {
			return yarn;
		}
		
		yarn = new Yarn(id, stock, cost, brand, colorway, Fiber.valueOf(primary), primaryPercent);
		return yarns.save(yarn);
	}
	
	@POST
	@Path("/{yarnId}")
	public Yarn purchased(
			@PathParam("yarnId") long id,
			@QueryParam("quantity") int quantity
			) {
		
		Yarn yarn = yarns.findById(id).orElse(null);
		
		if(yarn == null) {
			return null;
		}
		
		yarn.decrementQuantity(quantity);
		return yarns.save(yarn);
	}
	
	@DELETE
	@Path("/{yarnId}")
	public void removeOrder(@QueryParam("yarnId") long id) {
		yarns.deleteById(id);
	}
}
