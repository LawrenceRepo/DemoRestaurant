package com.lawrence.web.controller;

import com.lawrence.model.Restaurant;
import com.lawrence.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest //This annotation works by creating the ApplicationContext used
// in our tests through SpringApplication. It starts the embedded server,
// creates a web environment and then enables @Test methods to do integration testing.
@AutoConfigureMockMvc//Enables all auto-configuration related to MockMvc and ONLY MockMvc .
// Again, this is a subset of overall auto-configuration
class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestaurantService restaurantService;
    @Test
    void listAllRestaurants() throws Exception {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("restaurant");
        restaurantList.add(restaurant);
        when(restaurantService.listAllRestaurants()).thenReturn(restaurantList);
        mockMvc.perform(get("/viewRestaurants")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("/viewRestaurants"))
                .andExpect(model().attribute("restaurants",restaurantList));

    }
}