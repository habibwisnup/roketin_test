package com.habib.challenge2.services.impl;

import com.habib.challenge2.model.entities.Movie;
import com.habib.challenge2.model.requests.MovieRequest;
import com.habib.challenge2.model.responses.MovieResponse;
import com.habib.challenge2.repository.MovieRepository;
import com.habib.challenge2.services.interfaces.MovieService;
import com.habib.challenge2.utils.Mapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        logger.info("Saving new movie: {}", movieRequest.getTitle());
        Movie movie = new Movie(null, movieRequest.getTitle(), movieRequest.getDescription(),
                movieRequest.getDuration(), movieRequest.getArtists(),
                movieRequest.getGenres());
        Movie savedMovie = movieRepository.save(movie);
        return mapper.toResponse(savedMovie);
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        logger.info("Updating movie with ID: {}", id);
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setDuration(movieRequest.getDuration());
            movie.setArtists(movieRequest.getArtists());
            movie.setGenres(movieRequest.getGenres());
            Movie updatedMovie = movieRepository.save(movie);
            return mapper.toResponse(updatedMovie);
        }).orElseThrow(() -> {
            logger.error("Movie with ID {} not found", id);
            return new RuntimeException("Movie not found");
        });
    }

    @Override
    public Page<MovieResponse> listMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Page<MovieResponse> searchMovies(String keyword, Pageable pageable) {
        Specification<Movie> specification = (root, query, criteriaBuilder) -> {
            String likeKeyword = "%" + keyword.toLowerCase() + "%";

            Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likeKeyword);
            Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likeKeyword);
            Predicate artistsPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("artists")), likeKeyword);
            Predicate genresPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("genres")), likeKeyword);

            return criteriaBuilder.or(titlePredicate, descriptionPredicate, artistsPredicate, genresPredicate);
        };

        return movieRepository.findAll(specification, pageable).map(mapper::toResponse);
    }
}
