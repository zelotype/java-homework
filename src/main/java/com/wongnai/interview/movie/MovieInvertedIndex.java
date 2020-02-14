package com.wongnai.interview.movie;


import java.util.List;
import java.util.Set;

/**
 * An interface for movie inverted index to support another implementation e.g. Redis
 */
public interface MovieInvertedIndex {
    Set<Long> getIdsByKeyword(String keyword);

    Set<Long> getIdsByKeywords(List<String> keywords);

    void addMovie(Movie movie);
}
