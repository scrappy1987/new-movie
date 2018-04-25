package com.qa.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.service.business.MovieService;

@Path("/movie")
public class MovieEndpoint {

	private static final Logger LOGGER = Logger.getLogger(MovieEndpoint.class);

	@Inject
	private MovieService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		LOGGER.info("In MovieEndpoint getAllMovies ");
		return service.getAllMovies();
	}
	
	@Path("/json/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAMovie(@PathParam("id") Long id) {
		LOGGER.info("In MovieEndpoint getAMovie ");
		return service.getAMovie(id);
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String movie) {
		LOGGER.info("In MovieEndpoint addMovie ");
		return service.addMovie(movie);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteMovie(@PathParam("id") Long id) {
		LOGGER.info("In MovieEndpoint deleteMovie ");
		return service.deleteMovie(id);

	}

	public void setService(MovieService service) {
		this.service = service;
	}

}
