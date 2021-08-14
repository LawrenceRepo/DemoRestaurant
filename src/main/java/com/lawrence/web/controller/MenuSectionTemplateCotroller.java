package com.lawrence.web.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.lawrence.service.MenuSectionTemplateService;
import com.lawrence.web.dto.MenuSectionTemplateDto;

@Controller
public class MenuSectionTemplateCotroller {

    @Autowired
    MenuSectionTemplateService menuSectionTemplateService;

    static final String MODEL_ATTR = "result";

    static final String VIEW = "/result";


    @PostMapping("/addMenuSectionTemplate")
    @ExceptionHandler(IOException.class)
    public String addTemplate(@Valid MenuSectionTemplateDto menuSectionTemplateDto, BindingResult bindingResult, final Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute(MODEL_ATTR, "Missing fields ->" + bindingResult.getFieldErrors().get(0).getField());
            return VIEW;
        }
        menuSectionTemplateService.addTemplate(menuSectionTemplateDto);
        model.addAttribute(MODEL_ATTR, "added template");
        return VIEW;
    }

    @GetMapping("/getMenuTemplates")
    public String getMenuTemplates(final Model model) {
        model.addAttribute(MODEL_ATTR, menuSectionTemplateService.viewTemplates().toString());
        return VIEW;
    }
}

// localhost:8090/addMenuSectionTemplate?rank=1&name=rao&startTime=18/02/2021 10:12
// AM&endTime=19/02/2021 10:21 AM
// form data - picture, thumbnail, restaurants

// localhost:8090/getMenuTemplates
