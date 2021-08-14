package com.lawrence.web.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.lawrence.service.MenuItemService;
import com.lawrence.web.dto.DishCategoryMenuItemDto;
import com.lawrence.web.dto.DishMenuItemDto;
import com.lawrence.web.dto.TextMenuItemDto;

@Controller
// @ResponseBody
public class MenuItemController {
    @Autowired
    MenuItemService menuItemService;
    static final String VIEW = "/result";
    static final String MODEL_ATTR = "result";
    static final String VALIDATION_MSG = "Missing fields ->";
    
    @PostMapping("/addDishMenuItem")
    public String addDishMenuItem(final DishMenuItemDto dishMenuItemDto, BindingResult bindingResult, final Model model) throws IOException {
        if (bindingResult.hasErrors()) {

            model.addAttribute(MODEL_ATTR, VALIDATION_MSG + bindingResult.getFieldErrors().get(0).getField());
            return VIEW;
        }
        menuItemService.addDishMenuItem(dishMenuItemDto);
        model.addAttribute(MODEL_ATTR, "dish menu item added successfully");
        return VIEW;
    }

    @PostMapping("/addTextMenuItem")
    public String addTextMenuItem(final TextMenuItemDto textMenuItemDto, BindingResult bindingResult, final Model model) throws IOException {
        if (bindingResult.hasErrors()) {

            model.addAttribute(MODEL_ATTR, VALIDATION_MSG + bindingResult.getFieldErrors().get(0).getField());
            return VIEW;
        }
        menuItemService.addTextMenuItem(textMenuItemDto);
        model.addAttribute(MODEL_ATTR, "text menu item added successfully");
        return VIEW;
    }

    @PostMapping("/addDishCategoryMenuItem")
    public String addDishCategoryMenuItem(final DishCategoryMenuItemDto dishCategoryMenuItemDto, BindingResult bindingResult, final Model model) throws IOException {
        if (bindingResult.hasErrors()) {

            model.addAttribute(MODEL_ATTR, VALIDATION_MSG + bindingResult.getFieldErrors().get(0).getField());
            return VIEW;
        }
        menuItemService.addDishCategoryMenuItem(dishCategoryMenuItemDto);
        model.addAttribute(MODEL_ATTR, "dish category menu item added successfully");
        return VIEW;
    }

    @GetMapping("/viewMenuItems")
    public String viewMenuItems(final Model model) {
        model.addAttribute(MODEL_ATTR, menuItemService.viewMenuItems().toString());
        return VIEW;
    }
}

// localhost:8090/addMenuItem?section=1&title=breakfast&description=dfsfd&price=89
// form data - thumbnail

// localhost:8090/viewMenuItems
