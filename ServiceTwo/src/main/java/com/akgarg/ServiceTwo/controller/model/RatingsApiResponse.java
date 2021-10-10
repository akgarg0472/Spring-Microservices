package com.akgarg.ServiceTwo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingsApiResponse {

    private String message;
    private List<Rating> ratings;
}
