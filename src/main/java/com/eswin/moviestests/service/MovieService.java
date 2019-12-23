package com.eswin.moviestests.service;

import com.eswin.moviestests.data.MovieRepository;
import com.eswin.moviestests.model.Genre;
import com.eswin.moviestests.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByDuration(int duration) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= duration).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByName(String s) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().contains(s.toLowerCase()))
                .collect(Collectors.toList());
    }
}
