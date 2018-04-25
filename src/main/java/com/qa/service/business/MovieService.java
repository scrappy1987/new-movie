package com.qa.service.business;

public interface MovieService {

	String getAllMovies();
	
	String getAMovie(Long id);

	String addMovie(String movie);

	String deleteMovie(Long id);

}