package com.lawrence.web.controller;

import com.lawrence.Exception.CommonException;
import com.lawrence.service.DishCategoryToRestaurantService;
import com.lawrence.web.dto.DishCategoryToRestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class DishCategoryToRestaurantController {
    @Autowired
    DishCategoryToRestaurantService dishCategoryToRestaurantService;
    @PostMapping("/addDishCategoryToRestaurant")
    @ExceptionHandler(CommonException.class)
    public  String addCategory(final DishCategoryToRestaurantDto dishCategoryToRestaurantDto, final Model model) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        dishCategoryToRestaurantService.addDishCategory(dishCategoryToRestaurantDto);
        model.addAttribute("result","category added");
        return "/result" ;
    }

    @GetMapping("/viewDishCategoryToRestaurant")
    public String getAllCategories(final Model model){
        model.addAttribute("result",dishCategoryToRestaurantService.viewDishCategoriesToRestaurant());
        return "/result";
    }
}
