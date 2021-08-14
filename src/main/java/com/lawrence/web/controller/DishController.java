package com.lawrence.web.controller;

import com.lawrence.service.DishService;
import com.lawrence.web.dto.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class DishController {
    @Autowired
    DishService dishService;
    @PostMapping("/addDish")
    public String addDish(final DishDto dishDto, final Model model) throws IOException {
        dishService.addDish(dishDto);
        model.addAttribute("result","dish added");
        return "/result";
    }

    @GetMapping("/alldishes")
public String allDishes(final Model model)
    {
        model.addAttribute("result",dishService.allDishes());
        return "/result";
    }

}

//localhost:8090/addDish?code=RT12&name=Pasta&description=Pasta with cheese&employeePrice=100&guestPrice=100
// form data - supercategories[0],restaurants[0],thumbnail,picture

//localhost:8090/alldishes