package com.lawrence.web.controller;

import com.lawrence.model.Restaurant;
import com.lawrence.service.RestaurantService;
import com.lawrence.web.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;


@Controller

public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/addRestaurant")
    public String addPlant(final RestaurantDto restaurantDto) throws IOException {
        restaurantService.add(restaurantDto);
        return "/viewRestaurants";
    }

    @GetMapping("/viewRestaurants")
    public String listAllRestaurants(final Model model)
    {
        List<Restaurant> restaurantList = restaurantService.listAllRestaurants();
 model.addAttribute("restaurants",restaurantList);
 return  "/viewRestaurants";
    }
}
//localhost:8090/addRestaurant?name=sagar&street=Roger street&city=Mumbai&state=MH&pincode=400001&description=New Plant in country&plant=1