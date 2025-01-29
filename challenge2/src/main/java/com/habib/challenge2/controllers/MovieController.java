package com.habib.challenge2.controllers;

import com.habib.challenge2.model.requests.MovieRequest;
import com.habib.challenge2.model.responses.MovieResponse;
import com.habib.challenge2.services.interfaces.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<MovieResponse> uploadMovie(@Valid @RequestBody MovieRequest movieRequest) {
        logger.info("Uploading movie: {}", movieRequest.getTitle());
        MovieResponse response = movieService.saveMovie(movieRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        logger.info("Received request to update movie with ID: {}", id);
        return ResponseEntity.ok(movieService.updateMovie(id, movieRequest));
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> listMovies(Pageable pageable) {
        logger.info("Listing all movies");
        return ResponseEntity.ok(movieService.listMovies(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponse>> searchMovies(@RequestParam String keyword, Pageable pageable) {
        logger.info("Searching movies with keyword: {}", keyword);
        return ResponseEntity.ok(movieService.searchMovies(keyword, pageable));
    }
}
