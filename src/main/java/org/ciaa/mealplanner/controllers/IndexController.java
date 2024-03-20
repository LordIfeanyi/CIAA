package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    
    @PostMapping("/start")
    public String start(@RequestParam String choice) {

        System.out.println("Choice = " + choice);

        if(choice.equals("register")) {
            return "redirect:/register";
        } else if (choice.equals("login")) {
            return "redirect:/login";
        } else {
            return "redirect:/index";
        }
    }
}
