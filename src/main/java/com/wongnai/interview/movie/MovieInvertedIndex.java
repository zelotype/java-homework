package com.wongnai.interview.movie;


import java.util.List;
import java.util.Set;

public interface MovieInvertedIndex {
    Set<Long> getIdsByKeyword(String keyword);

    Set<Long> getIdsByKeywords(List<String> keywords);

    void addMovie(Movie movie);
}
