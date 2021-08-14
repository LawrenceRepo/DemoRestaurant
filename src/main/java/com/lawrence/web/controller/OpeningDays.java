package com.lawrence.web.controller;

import com.lawrence.service.OpeningDayService;
import com.lawrence.web.dto.OpeningDayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addOpeningDays")
public class OpeningDays {
    @Autowired
    OpeningDayService openingDayService;
    @PostMapping
    public String addOpeningDays(final OpeningDayDto openingDayDto, final Model model)
    {
        openingDayService.addOpeningDay(openingDayDto);
         model.addAttribute("result","opening day added successfully");
         return "/result";
    }
}
