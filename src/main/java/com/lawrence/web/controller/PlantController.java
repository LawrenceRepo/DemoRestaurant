package com.lawrence.web.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.lawrence.service.PlantService;
import com.lawrence.web.dto.PlantDto;

@Controller
public class PlantController {

    @Autowired
    PlantService plantService;

    static final String VIEW = "/result";

    static final String MODEL_ATTR = "result";

    @PostMapping("/addPlant")
    public String addPlant(final PlantDto plantDto, Model model) {
        try {
            plantService.addPlant(plantDto);
        } catch (IOException e) {
            model.addAttribute(MODEL_ATTR, e.getMessage());
            return "/viewPlants";
        }
        model.addAttribute(MODEL_ATTR, "Plant added");
        return "/viewPlants";
    }

    @GetMapping("/viewPlants")
    public String viewPlants(final Model model) {
        model.addAttribute(MODEL_ATTR, plantService.getAllPlants());
        return VIEW;
    }
}
