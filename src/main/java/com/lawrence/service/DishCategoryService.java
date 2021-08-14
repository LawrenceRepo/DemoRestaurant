package com.lawrence.service;

import com.lawrence.model.DishCategory;
import com.lawrence.web.dto.DishCategoryDto;

import java.io.IOException;
import java.util.List;

public interface DishCategoryService {
    DishCategory addDishCategory(DishCategoryDto dishCategoryDto) throws IOException;
    List<DishCategory> viewDishCategories();
}
