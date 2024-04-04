package io.openliberty.shop;

import java.util.List;

import io.openliberty.entities.Pattern;
import io.openliberty.models._Pattern;
import io.openliberty.repositories.Patterns;
import jakarta.data.Limit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// View patterns

@Path("/dictonary")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Dictonary {
	
	@Inject
	private Patterns patterns;
	
	@GET
	@Path("/{patternID}")
	public Pattern getPattern(@PathParam("patternId") long id) {
		return patterns.findPattern(id).orElse(null);
	}
	
	@GET
	@Path("/{author}")
	public List<Pattern> getPatternsByAuthor(@PathParam("author") String author) {
		return patterns.findAuthoredWorks(author);
	}
	
	@GET
	@Path("promoted/{author}")
	public Pattern[] getPromotedPatternsByAuthor(@PathParam("author") String author) {
		return patterns.patternByAuthor(author, Limit.of(5), _Pattern.cost.asc());
	}

}
