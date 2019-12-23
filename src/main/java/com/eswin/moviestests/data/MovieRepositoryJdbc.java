package com.eswin.moviestests.data;

import com.eswin.moviestests.model.Genre;
import com.eswin.moviestests.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        Object[] args = {movie.getName(), movie.getMinutes(), movie.getGenre().toString()};
        jdbcTemplate.update("insert into movies (name, minutes, genre) values (?, ?, ?)", args);
    }

    private static RowMapper<Movie> movieMapper = (resultSet, i) ->
            new Movie(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("minutes"),
            Genre.valueOf(resultSet.getString("genre"))
    );
}
