package com.lawrence.web.controller;


import com.lawrence.service.OpeningScheduleService;
import com.lawrence.web.dto.OpeningScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OpeningScheduleController {

    @Autowired
    OpeningScheduleService openingScheduleService;
    @PostMapping("/addOpeningSchedule")
    public String addOpeningSchedule(final OpeningScheduleDto openingScheduleDto, final Model model)
    {
      openingScheduleService.addOpeningSchedule(openingScheduleDto);
      model.addAttribute("result","opening schedule added");
      return "/result";
    }
}
