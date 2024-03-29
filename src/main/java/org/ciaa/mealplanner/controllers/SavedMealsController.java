package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the saved meals
 * page of the application, "savedMeals.html".
 */
@Controller
public class SavedMealsController {

    /**
     * Handles GET requests from "savedMeals.html". Responsible for displaying
     * "savedMeals.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/savedMeals")
    public String savedMeals() {
        return "savedMeals";
    }

    /**
     * Handles POST requests from "savedMeals.html". If the "Return to Home page"
     * button is pressed, redirects the user to "home.html".
     * 
     * @param submitFormButton the result of the button press from
     *                         "savedMeals.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/savedMeals")
    public String savedMeals(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/savedMeals";
    }
}
