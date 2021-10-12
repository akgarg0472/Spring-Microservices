package com.akgarg.ServiceOne.controller;

import com.akgarg.ServiceOne.model.Movie;
import com.akgarg.ServiceOne.model.MovieResponse;
import com.akgarg.ServiceOne.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/movie")
public class MainController {

    @Value("${tmdb.api.key}")
    private String api_key;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public MovieResponse getMovie(@PathVariable String movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + api_key;

        try {
            MovieSummary movieSummary = this.restTemplate.getForObject(url, MovieSummary.class);
            Movie movie = new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());
            return new MovieResponse("Movie information fetched successfully", movie, 200);
        } catch (Exception e) {
            return new MovieResponse(e.getMessage(), null, 404);
        }
    }
}
