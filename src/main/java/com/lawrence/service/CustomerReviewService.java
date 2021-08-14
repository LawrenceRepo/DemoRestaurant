package com.lawrence.service;

import com.lawrence.model.CustomerReview;
import com.lawrence.web.dto.CustomerReviewDto;

import java.util.List;

public interface CustomerReviewService {
    void addReview(CustomerReviewDto customerReviewDto);
     List<CustomerReview> getAllReviews();
}
