package com.lawrence.web.controller;

import com.lawrence.model.UserEntity;
import com.lawrence.service.UserService;
import com.lawrence.web.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class Registration {


    @Autowired
    private UserService userService;


    @PostMapping
    public String userRegistration(final UserData userData, final BindingResult bindingResult, final Model model) {
        UserEntity userEntity=null;
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "/register";
        }
        try {
            userEntity = userService.register(userData);
        } catch (Exception e) {
            bindingResult.rejectValue("email", "userData.email", "An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "/registerSu";
        }
        
        model.addAttribute("registrationMsg", userEntity);
        return "/result";
    }

}