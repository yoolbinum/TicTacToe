package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String homepage(Model model, Authentication auth) {
        if (auth != null)
            model.addAttribute("user", userService.findByUsername(auth.getName()));

        return "index";
    }
}
