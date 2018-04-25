package com.qa.service.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.domain.Movie;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class MovieMapRepository implements MovieRepository {

	private static final Logger LOGGER = Logger.getLogger(MovieMapRepository.class);
	
	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Movie> movieMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	public MovieMapRepository() {
		this.movieMap = new HashMap<Long, Movie>();
		ID = INITIAL_COUNT;
		initMovieMap();
	}

	public String getAllMovies() {
		LOGGER.info("In MovieMapRepository getAllMovies");
		return util.getJSONForObject(movieMap.values());
	}
	
	public String getMovie(Long id) {
		LOGGER.info("In MovieMapRepository getMovie");
		return util.getJSONForObject(movieMap.get(id));
	}

	public String createMovie(String movie) {
		LOGGER.info("In MovieMapRepository createMovie");
		ID++;
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		movieMap.put(ID, newMovie);
		return movie;
	}

	public String deleteMovie(Long id) {
		LOGGER.info("In MovieMapRepository deleteMovie");
		movieMap.remove(id);
		return "{\"message\": \"movie sucessfully removed\"}";
	}

	private void initMovieMap() {
		Movie movie = new Movie("IT", "Horror", "18");
		movieMap.put(1L, movie);
	}

	

}
