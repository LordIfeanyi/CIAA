package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.models.UserSignIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loggedin")
    public String register(@ModelAttribute UserSignIn userSignIn) {
        
        // validation
        System.out.println("passed UserSignIn: " + userSignIn.toString());
        
        boolean result = Control.checkIfExists(userSignIn);

        // validation
        System.out.println("Control.checkIfExists() returned " + result);
        
        if(result) { // user credentials exist in "users"
            return "home"; // go to home page
        } else {
            return "login";
        }
    }
}
