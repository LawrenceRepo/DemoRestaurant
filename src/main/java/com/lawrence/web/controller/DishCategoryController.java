package com.lawrence.web.controller;

import com.lawrence.Exception.CommonException;
import com.lawrence.service.CategoryService;
import com.lawrence.web.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class DishCategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/addDishCategory")
    @ExceptionHandler(CommonException.class)
    public  String addCategory(final CategoryDto categoryDto, final Model model) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        categoryService.addCategory(categoryDto);
        model.addAttribute("result","category added");
        return "/result" ;
    }

    @GetMapping("/viewDishCategories")
    public String getAllCategories(final Model model){
        model.addAttribute("result",categoryService.getAllCategories());
        return "/result";
    }
}
//localhost:8090/addDishCategory
//form data - thumbnail,picture,products,restaurants

//localhost:8090/viewDishCategories

