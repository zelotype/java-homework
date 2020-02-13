package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class

		List<Movie> matchedMovies = new ArrayList<Movie>();
		List<MovieData> movies = movieDataService.fetchAll();

		movies.forEach(movie -> {
			List<String> splitedTitle = Arrays.asList(movie.getTitle().split(" "));
			splitedTitle.forEach(title -> {
				if(title.equalsIgnoreCase(queryText)){
					matchedMovies.add(new Movie(movie.getTitle(), movie.getCast()));
				}
			});
		});

		return matchedMovies;
	}
}
