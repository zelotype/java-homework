package com.wongnai.interview.movie;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class use a Hashmap to implement an in-memory inverted index.
 */
@Component("hashMapMovieInvertedIndex")
public class HashMapMovieInvertedIndex implements MovieInvertedIndex {

    private Map<String, Set<Long>> invertedIndex = new HashMap<>();

    @Override
    public Set<Long> getIdsByKeyword(String keyword) {
        // If there is no keyword is existed, return an empty set
        if(invertedIndex.containsKey(keyword)){
            return invertedIndex.get(keyword);
        }
        return Collections.emptySet();
    }

    @Override
    public Set<Long> getIdsByKeywords(List<String> keywords) {
        // Reduce list of set into single set by intersection
        // [(1, 5, 9), (2, 5), (5, 8)] -> (5)
        return keywords.stream()
                .map(this::getIdsByKeyword)
                .reduce(Sets::intersection)
                .orElseGet(HashSet::new);
    }

    @Override
    public void addMovie(Movie movie) {
        for (String keyword : movie.getName().toLowerCase().split(" ")) {
            if (invertedIndex.containsKey(keyword)) {
                invertedIndex.get(keyword).add(movie.getId());
            } else {
                Set<Long> ids = new HashSet<>();
                ids.add(movie.getId());
                invertedIndex.put(keyword, ids);
            }
        }
    }
}
