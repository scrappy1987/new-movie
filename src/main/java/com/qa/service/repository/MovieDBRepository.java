package com.qa.service.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.domain.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class MovieDBRepository implements MovieRepository {
	
	private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;
	
	public String getAllMovies() {
		LOGGER.info("In MovieDBRepository getAllMovies");
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(movies);
	}

	public String getMovie(Long id) {
		Movie movieInDB = findMovie(id);
		if (movieInDB != null) {
			return util.getJSONForObject(movieInDB);
		}
		else {
			return "{\"message\":\"movie not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		LOGGER.info("In MovieDBRepository createMovie");
		Movie movieObj = util.getObjectForJSON(movie, Movie.class);
		manager.persist(movieObj);
		return "{\"message\": \"m has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		LOGGER.info("In MovieDBRepository deleteMovie");
		Movie movieInDB = findMovie(id);
		if (movieInDB != null) {
			manager.remove(movieInDB);
			return "{\"message\": \"movie sucessfully deleted\"}";
		}
		else {
			return "{\"message\": \"movie not found\"}";
		}
		
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	

}
