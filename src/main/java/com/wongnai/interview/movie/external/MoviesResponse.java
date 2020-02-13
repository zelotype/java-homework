package com.wongnai.interview.movie.external;

import java.util.ArrayList;
import java.util.Arrays;

public class MoviesResponse extends ArrayList<MovieData> {
    public MoviesResponse(MovieData[] movies) {
        this.addAll(Arrays.asList(movies));
    }
}
