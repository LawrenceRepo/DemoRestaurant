package com.lawrence.service.Impl;

import com.lawrence.model.CustomerReview;
import com.lawrence.model.Restaurant;
import com.lawrence.model.UserEntity;
import com.lawrence.repository.CutomerReviewRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.repository.UserRepo;
import com.lawrence.service.CustomerReviewService;
import com.lawrence.service.impl.CustomerReviewServiceImpl;
import com.lawrence.web.dto.CustomerReviewDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class CustomerReviewServiceImplTest {
    @InjectMocks
    CustomerReviewServiceImpl customerReviewService;
    @Mock
    CutomerReviewRepo CutomerReviewRepo;
    @Mock
    ProductRepo productRepo;
    @Mock
    UserRepo userRepo;
    @Test
    void addReview() {
        CustomerReviewDto customerReviewDto = new CustomerReviewDto();
        customerReviewDto.setComment("comment");
        customerReviewDto.setHeadline("comm");
        customerReviewDto.setRating(4.5);
        customerReviewDto.setUser("sa@gmail.com");
        customerReviewDto.setProduct(1);
        customerReviewService.addReview(customerReviewDto);
    }

    @Test
    void getAllReviews() {
        List<CustomerReview> customerReviews = new ArrayList<>();
        CustomerReview customerReview1 = new CustomerReview();
        customerReview1.setComment("comment 1");
        customerReviews.add(customerReview1);
        CustomerReview customerReview2 = new CustomerReview();
        customerReview2.setComment("comment 2");
        customerReviews.add(customerReview2);
        when(customerReviewService.getAllReviews()).thenReturn(customerReviews);
        List<CustomerReview> customerReviewList = customerReviewService.getAllReviews();
        assertEquals(2, customerReviewList.size());
//        verify(customerReviewService, times(1)).getAllReviews();
    }
}