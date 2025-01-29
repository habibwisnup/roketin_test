package com.habib.challenge2.repository.custom.impl;

import com.habib.challenge2.model.entities.Movie;
import com.habib.challenge2.repository.custom.interfaces.MovieCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieCustomRepositoryImpl implements MovieCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Movie> searchMovies(String keyword, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> movie = query.from(Movie.class);

        List<Predicate> predicates = new ArrayList<>();
        String likeKeyword = "%" + keyword.toLowerCase() + "%";

        predicates.add(cb.like(cb.lower(movie.get("title")), likeKeyword));
        predicates.add(cb.like(cb.lower(movie.get("description")), likeKeyword));
        predicates.add(cb.like(cb.lower(movie.get("artists")), likeKeyword));
        predicates.add(cb.like(cb.lower(movie.get("genres")), likeKeyword));

        query.where(cb.or(predicates.toArray(new Predicate[0])));

        TypedQuery<Movie> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Movie> countRoot = countQuery.from(Movie.class);
        countQuery.select(cb.count(countRoot)).where(cb.or(predicates.toArray(new Predicate[0])));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(typedQuery.getResultList(), pageable, count);
    }
}
