package com.moviedb.service;

import com.moviedb.model.Movies;
import com.moviedb.repositories.MovieRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	private MovieRepository movieRepository;

	public UserDetailsServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Movies movies = movieRepository.findByTitle(username);
		if (movies == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(movies.getTitle(), movies.getImdb(), emptyList());
	}
}
