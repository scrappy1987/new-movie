package com.qa.service.repository;

public interface MovieRepository {

	String getAllMovies();
	
	String getMovie(Long id);

	String createMovie(String movie);

	String deleteMovie(Long id);

}