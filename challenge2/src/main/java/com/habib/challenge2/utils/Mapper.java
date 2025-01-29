package com.habib.challenge2.utils;

import com.habib.challenge2.model.entities.Movie;
import com.habib.challenge2.model.responses.MovieResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getArtists(),
                movie.getGenres()
        );
    }
}
