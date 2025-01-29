package com.habib.challenge2.services.interfaces;

import com.habib.challenge2.model.requests.MovieRequest;
import com.habib.challenge2.model.responses.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    MovieResponse saveMovie(MovieRequest movieRequest);
    MovieResponse updateMovie(Long id, MovieRequest movieRequest);
    Page<MovieResponse> listMovies(Pageable pageable);
    Page<MovieResponse> searchMovies(String keyword, Pageable pageable);
}
