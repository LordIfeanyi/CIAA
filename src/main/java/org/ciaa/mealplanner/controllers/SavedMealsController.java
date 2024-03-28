package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SavedMealsController {
    
    @GetMapping("/savedMeals")
    public String savedMeals() {
        return "savedMeals";
    }

    @PostMapping("/savedMeals")
    public String savedMeals(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/savedMeals";
    }
}
