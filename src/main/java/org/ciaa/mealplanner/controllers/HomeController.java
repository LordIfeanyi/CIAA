package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String home(@RequestParam("submitFormButton") String submitFormButton) {

        if(submitFormButton.equals("userInfo")) {
            return "redirect:/userInfo";
        } else if(submitFormButton.equals("savedMeals")) {
            return "redirect:/savedMeals";
        } else if (submitFormButton.equals("mealSearch")) {
            return "redirect:/mealSearch";
        } else if (submitFormButton.equals("mealSuggest")) {
            return "redirect:/mealSuggest";
        } else {
            return "redirect:/home";
        }
    }
    
}
