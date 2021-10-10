package com.akgarg.ServiceMain.controller;

import com.akgarg.ServiceMain.model.Movie;
import com.akgarg.ServiceMain.model.MovieInfo;
import com.akgarg.ServiceMain.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"SpellCheckingInspection", "ConstantConditions", "unused"})
@RestController
@RequestMapping("/v1/get-movie")
public class RestTemplateController {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;  // used to fetch the microservice clients

    @Autowired
    public RestTemplateController(RestTemplate restTemplate,
                                  DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }


    @SuppressWarnings("UnnecessaryLocalVariable")
    @RequestMapping("/{userId}")
    public List<MovieInfo> getMovie(@PathVariable String userId) {

        // assume this list is coming from the rating microservice with the help of userId

        // fetching and displaying all microservices
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }


        List<Rating> ratings = Arrays.asList(
                new Rating("abcd", 1),
                new Rating("efgh", 2),
                new Rating("ijkl", 3),
                new Rating("mnop", 4)
        );

        List<MovieInfo> movies = ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-microservice-one/movie/" + rating.getId(), Movie.class);
                    return new MovieInfo(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());

        return movies;
    }
}
