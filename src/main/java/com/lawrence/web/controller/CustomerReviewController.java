package com.lawrence.web.controller;

import com.lawrence.service.CustomerReviewService;
import com.lawrence.web.dto.CustomerReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerReviewController {
    @Autowired
    CustomerReviewService customerReviewService;

    @PostMapping("/addCustomerReview")
    public String addCustomerReview(final CustomerReviewDto customerReviewDto, final Model model)
    {
        customerReviewService.addReview(customerReviewDto);
        model.addAttribute("result","review submitted");
        return "/result";
    }

    @GetMapping("/viewCustomerReviews")
    public String viewCustomerReviews(final Model model)
    {
        model.addAttribute("result",customerReviewService.getAllReviews());
        return "/result";
    }
}
