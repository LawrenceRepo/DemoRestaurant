package com.lawrence.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawrence.model.CustomerReview;
import com.lawrence.repository.CutomerReviewRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.repository.UserRepo;
import com.lawrence.service.CustomerReviewService;
import com.lawrence.web.dto.CustomerReviewDto;

@Service
public class CustomerReviewServiceImpl implements CustomerReviewService {
    @Autowired
    CutomerReviewRepo customerReviewRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public void addReview(CustomerReviewDto customerReviewDto) {
        CustomerReview customerReview = new CustomerReview();
        BeanUtils.copyProperties(customerReviewDto,customerReview);
        customerReview.setProduct(productRepo.getById(customerReviewDto.getProduct()));
        customerReview.setUserEntity(userRepo.findByEmail(customerReviewDto.getUser()));
        customerReviewRepo.save(customerReview);
    }
    @Override
    public List<CustomerReview> getAllReviews()
    {
        return customerReviewRepo.findAll();
    }
}
