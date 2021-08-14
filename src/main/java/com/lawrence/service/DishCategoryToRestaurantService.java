package com.lawrence.service;

import com.lawrence.model.DishCategoryToRestaurant;
import com.lawrence.web.dto.DishCategoryToRestaurantDto;

import java.io.IOException;
import java.util.List;

public interface DishCategoryToRestaurantService {
    void addDishCategory(DishCategoryToRestaurantDto dishCategoryToRestaurantDto) throws IOException;
    List<DishCategoryToRestaurant> viewDishCategoriesToRestaurant();
}
