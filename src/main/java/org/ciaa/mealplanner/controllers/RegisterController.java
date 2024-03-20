package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {

        if(user != null) {

            // validation
            System.out.println(user.toString());

            Control.addUser(user);

            // validation
            Control.printUsers();
            
            return "redirect:/login";
            
        } else {
            return "redirect:/register";
        }
    }
}
