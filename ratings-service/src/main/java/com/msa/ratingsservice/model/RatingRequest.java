package com.msa.ratingsservice.model;

import lombok.Data;

@Data
public class RatingRequest {

    private String name;
    private double stars;
}
