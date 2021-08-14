package com.lawrence.web.controller;

import com.lawrence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password)
    {
        try{
   userService.verifyUser(username, password);
        }catch(Exception e)
        {
            return "/error";
        }
        return "/home";
    }

    @PostMapping("/changePassword")
    public String changePassword(final Model model,@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword)
    {
        model.addAttribute("result",userService.changePassword(username,oldPassword,newPassword));
        return "/result";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(final Model model,@RequestParam String username)
    {
        model.addAttribute("result",userService.resetPassword(username));
        return "/result";
    }
}
