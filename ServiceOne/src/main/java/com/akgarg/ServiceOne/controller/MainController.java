package com.akgarg.ServiceOne.controller;

import com.akgarg.ServiceOne.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/movie")
public class MainController {

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable String movieId) {
        return new Movie(movieId, "movie name: " + movieId.length(), "description of movie with id: " + movieId);
    }
}
