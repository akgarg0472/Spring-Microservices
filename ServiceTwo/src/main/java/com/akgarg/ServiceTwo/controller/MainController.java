package com.akgarg.ServiceTwo.controller;

import com.akgarg.ServiceTwo.controller.model.Rating;
import com.akgarg.ServiceTwo.controller.model.RatingsApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rating")
public class MainController {

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 1 + new Random().nextInt(5));
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public RatingsApiResponse getRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = new ArrayList<>();
        for (int i = 0; i < userId.length(); i++) {
            ratings.add(new Rating(userId + "->" + i, 1 + new Random().nextInt(5)));
        }

        return new RatingsApiResponse("Ratings generated successfully", ratings);
    }
}
