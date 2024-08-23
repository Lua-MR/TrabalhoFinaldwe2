package com.example.leitura.controller;

import com.example.leitura.model.User;
import com.example.leitura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register"; 
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
    	userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    
    public String showLoginForm() {
        return "login"; 
    }
    
    @PostMapping("/login")
    public String loginUser() {
        return "redirect:/readings"; 
    }
    

}
