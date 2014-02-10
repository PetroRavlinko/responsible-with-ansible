package no.bekk.jersey.resources;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.bekk.db.MySQLAccess;
import no.bekk.jersey.filters.NoCacheResponseFilter;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/")
@ResourceFilters(NoCacheResponseFilter.class)
public class RootResource {

       private MySQLAccess dbAccess = new MySQLAccess();

	@GET
	@Produces(TEXT_PLAIN)
	public Response ping() throws Exception {
		return Response.ok("I'm alive :) " + dbAccess.sayHello()).build();
	}

}
