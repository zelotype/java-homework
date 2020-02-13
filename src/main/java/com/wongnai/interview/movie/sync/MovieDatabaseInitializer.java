package com.wongnai.interview.movie.sync;

import com.wongnai.interview.movie.MovieInvertedIndex;
import com.wongnai.interview.movie.MovieRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("movieDatabaseInitializer")
public class MovieDatabaseInitializer implements InitializingBean {
	@Autowired
	private MovieDataSynchronizer movieDataSynchronizer;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	@Qualifier("hashMapMovieInvertedIndex")
	private MovieInvertedIndex invertedIndex;

	@Override
	public void afterPropertiesSet() throws Exception {
		//run sync while server is starting
		movieDataSynchronizer.forceSync();

		movieRepository.findAll().forEach(invertedIndex::addMovie);
	}
}
