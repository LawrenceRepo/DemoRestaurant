package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.CustomerReview;

public interface CutomerReviewRepo extends JpaRepository<CustomerReview,Long> {
}
