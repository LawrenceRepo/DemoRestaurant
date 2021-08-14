package com.lawrence.service;

import com.lawrence.model.Restaurant;
import com.lawrence.web.dto.RestaurantDto;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {
    Restaurant add(final RestaurantDto restaurantdto) throws IOException;
    List<Restaurant> listAllRestaurants();
}
