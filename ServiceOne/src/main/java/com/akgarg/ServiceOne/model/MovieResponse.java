package com.akgarg.ServiceOne.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieResponse {

    private String message;
    private Movie payload;
    private int code;
}
