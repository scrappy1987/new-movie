package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.MovieRepository;

public class MovieServiceImpl implements MovieService {

	private static final Logger LOGGER = Logger.getLogger(MovieService.class);

	@Inject
	private MovieRepository repo;
	
	public String getAllMovies() {
		LOGGER.info("In MovieServiceImpl getAllMovies ");
		return repo.getAllMovies();
	}

	public String getAMovie(Long id) {
		LOGGER.info("In MovieServiceImpl getAMovie ");
		return repo.getMovie(id);
	}

	public String addMovie(String movie) {
		LOGGER.info("In MovieServiceImpl addMovie ");
		return repo.createMovie(movie);
	}

	public String deleteMovie(Long id) {
		LOGGER.info("In MovieServiceImpl deleteMovie ");
		return repo.deleteMovie(id);
	}
	
	public void setRepo(MovieRepository repo) {
		this.repo = repo;
	}

	
}
