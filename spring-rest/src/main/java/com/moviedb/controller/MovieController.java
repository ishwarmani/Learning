package com.moviedb.controller;

import com.moviedb.model.Greeting;
import com.moviedb.model.Movies;
import com.moviedb.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }*/

    @RequestMapping("/movies/year/{year}")
    public List<Movies> moviesList(@PathVariable("year") int year){
        return movieRepository.findAllByYear(year);
    }

    @RequestMapping("/movies/imdbid/{imdb}")
    public Movies getMovieByImdb(@PathVariable("imdb") String imdb){
        Movies movies = movieRepository.findByImdb(imdb);
        if(movies == null){
            System.out.println("No movie exist with given id");
        }
        return movies;
    }

    @PostMapping("/movie/create")
    public ResponseEntity<Void> saveMovie(@Valid @RequestBody Movies movie,UriComponentsBuilder ucBuilder){
        Movies movies = movieRepository.findByImdb(movie.getImdb());
        if(movies != null){
            System.out.println("A movie already exists with this imdb id");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        movie.setImdb(bCryptPasswordEncoder.encode(movie.getImdb()));
        movieRepository.save(movie);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(movie.getImdb()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="movies/update/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Movies> updateMovie(@PathVariable("id") String id, @RequestBody Movies movies){
        Movies movie = movieRepository.findByImdb(id);
        if(movie == null){
            System.out.println("Sorry no movie exist with this id.");
            return new ResponseEntity<Movies>(HttpStatus.NOT_FOUND);
        }else{
            movie.setTitle(movies.getTitle());
            movie.setType(movies.getType());
            movie.setYear(movies.getYear());

            movieRepository.save(movie);
            return new ResponseEntity<Movies>(movie,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "movies/delete/{imdb}", method = RequestMethod.DELETE)
    public ResponseEntity<Movies> deleteMovie(@PathVariable("imdb") String imdb){
        Movies movies = movieRepository.findByImdb(imdb);
        if(movies == null){
            System.out.println("No movie exists with this imdb id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            movieRepository.deleteByImdb(imdb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
