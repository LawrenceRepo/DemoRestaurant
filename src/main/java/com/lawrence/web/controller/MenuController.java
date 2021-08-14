package com.lawrence.web.controller;

import com.lawrence.service.MenuService;
import com.lawrence.web.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    @PostMapping("/addMenu")
    public String addMenu(MenuDto menuDto, final Model model)
    {
        menuService.addMenu(menuDto);
        model.addAttribute("result","menu added");
        return "/result";
    }
    @GetMapping("/viewMenus")
    public String viewMenus( final Model model)
    {
        model.addAttribute("result",menuService.listMenus().toString());
        return "/result";
    }

}


