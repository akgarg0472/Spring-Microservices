package com.akgarg.ServiceMain.controller;

import com.akgarg.ServiceMain.model.Movie;
import com.akgarg.ServiceMain.model.MovieInfo;
import com.akgarg.ServiceMain.model.Rating;
import com.akgarg.ServiceMain.model.RatingsApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection", "unused"})
@RestController
@RequestMapping("/v2/get-movie")
public class WebClientController {

    private final WebClient.Builder webClientBuilder;
    private final RestTemplate restTemplate;

    @Autowired
    public WebClientController(WebClient.Builder webClientBuilder,
                               RestTemplate restTemplate) {
        this.webClientBuilder = webClientBuilder;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    public List<MovieInfo> getMovie(@PathVariable String userId) {
        // assume this list is coming from the rating microservice with the help of userId
        RatingsApiResponse ratingsApiResponse =
                this.restTemplate.getForObject("http://rating-microservice-two/rating/user/" + userId, RatingsApiResponse.class);
        List<Rating> ratings = ratingsApiResponse.getRatings();

        return ratings.stream()
                .map(rating -> {
                    Movie movie = webClientBuilder.build(). // builds a new client
                            get()   // method type of request (couldbe POST, DELETE etc.)
                            .uri("http://movie-microservice-one/movie/" + rating.getId())  // URI where to make request
                            .retrieve() // fetch content from the requested uri
                            .bodyToMono(Movie.class)    // converts received data string into provided class object
                            .block();
                    // blocks execution of further LOCs to prevent data corruption or exceptions,
                    // makes this request kind of synchronous

                    // bodyToMono is actually reactive way to say that we will get object not right now
                    // but after few monents. It's like a promise that we will surely get this object asynchronously.
                    return new MovieInfo(movie.getName(),
                            movie.getDescription(),
                            rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
