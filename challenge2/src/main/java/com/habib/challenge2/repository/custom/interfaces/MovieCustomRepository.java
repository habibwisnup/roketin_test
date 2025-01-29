package com.habib.challenge2.repository.custom.interfaces;

import com.habib.challenge2.model.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieCustomRepository {
    Page<Movie> searchMovies(String keyword, Pageable pageable);
}
