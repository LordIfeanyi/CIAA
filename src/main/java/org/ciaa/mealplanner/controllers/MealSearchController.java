package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the meal search
 * page of the application, "mealSearch.html".
 */
@Controller
public class MealSearchController {

    /**
     * Handles GET requests from "mealSearch.html". Responsible for displaying
     * "mealSearch.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/mealSearch")
    public String mealSearch() {
        return "mealSearch";
    }

    /**
     * Handles POST requests from "mealSearch.html". If the "Return to Home page"
     * button is pressed, redirects the user to "home.html".
     * 
     * @param submitFormButton the result of the button press from
     *                         "mealSearch.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/mealSearch")
    public String mealSearch(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/mealSearch";
    }
}
