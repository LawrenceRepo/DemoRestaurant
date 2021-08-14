package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class CustomerReviewDto {
    private String headline;
    private String comment;
    private double rating;
    @NotEmpty
    private String user;
    @NotEmpty
    private long product;
}
