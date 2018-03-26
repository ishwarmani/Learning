package com.moviedb.repositories;

import com.moviedb.model.Movies;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movies,String>{

    Movies findByTitle(String title);

    List<Movies> findAllByYear(int year);

    Movies findByImdb(String imdb);

    void deleteByImdb(String imdb);

}
