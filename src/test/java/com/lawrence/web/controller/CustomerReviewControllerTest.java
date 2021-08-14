package com.lawrence.web.controller;

import com.lawrence.service.CustomerReviewService;
import com.lawrence.web.dto.CustomerReviewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.

@AutoConfigureMockMvc
//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class CustomerReviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean // mockito annotation to create mock of objects
    CustomerReviewService customerReviewService;

    @Test
    void viewCustomerReviews() throws Exception {
        CustomerReviewDto customerReviewDto = new CustomerReviewDto();
        customerReviewDto.setComment("Good");
        customerReviewDto.setProduct(1);
        customerReviewDto.setUser("ru@gmail.com");
        customerReviewDto.setHeadline("Positive review");
        customerReviewDto.setRating(4.5);
        customerReviewService.addReview(customerReviewDto);
        this.mockMvc.perform(post("/addCustomerReview")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/result"))
                .andExpect(model().attribute("result","review submitted"));

    }
}