package com.lawrence.service;

import com.lawrence.model.Dish;
import com.lawrence.web.dto.DishDto;

import java.io.IOException;
import java.util.List;

public interface DishService {
Dish addDish(DishDto dish) throws IOException;
List<Dish> allDishes();
}
