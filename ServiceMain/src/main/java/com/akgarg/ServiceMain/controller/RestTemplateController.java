package com.akgarg.ServiceMain.controller;

import com.akgarg.ServiceMain.model.Movie;
import com.akgarg.ServiceMain.model.MovieInfo;
import com.akgarg.ServiceMain.model.MovieResponse;
import com.akgarg.ServiceMain.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"SpellCheckingInspection", "ConstantConditions", "unused", "Convert2MethodRef"})
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
    @HystrixCommand(fallbackMethod = "getFallbackMovieInfoList",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public List<MovieInfo> getMovie(@PathVariable String userId) {

        // fetching and displaying all microservices
        // List<String> services = discoveryClient.getServices();
        // for (String service : services) {
        //    System.out.println(service);
        // }

        // intentional issue generated to indefinitely delay response
//        int foo = -1;
//        while (foo < 5) {
//            foo += 1;
//            foo -= 1;
//        }

        // assume this list is coming from the rating microservice with the help of userId
        List<Rating> ratings = Arrays.asList(
                new Rating("100", 1),
                new Rating("200", 2),
                new Rating("300", 3),
                new Rating("400", 4)
        );

        List<MovieInfo> movies = ratings.stream()
                .map(rating -> getMovieInfo(rating))
                .collect(Collectors.toList());

        return movies;
    }


    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    private MovieInfo getMovieInfo(Rating rating) {
        MovieResponse movieResponse = restTemplate.getForObject("http://movie-microservice-one/movie/" + rating.getId(), MovieResponse.class);
        if (movieResponse != null) {
            Movie movie = movieResponse.getPayload();
            return new MovieInfo(movie.getName(), movie.getDescription(), rating.getRating());
        }
        return new MovieInfo(null, null, -1);
    }


    // fallback method if rest call is failed or timeout or whatsoever happended with this cutie
    private List<MovieInfo> getFallbackMovieInfoList(@PathVariable String userId) {
        return Collections.singletonList(new MovieInfo(null, null, -1));
    }


    // fallback method if rest call is failed or timeout or whatsoever happended with this cutie
    private MovieInfo getFallbackMovieInfo(Rating rating) {
        return new MovieInfo(null, null, -1);
    }
}
