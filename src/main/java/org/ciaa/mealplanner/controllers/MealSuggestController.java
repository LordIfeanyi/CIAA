package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the meal
 * suggester page of the application, "mealSuggest.html".
 */
@Controller
public class MealSuggestController {

    /**
     * Handles GET requests from "mealSuggest.html". Responsible for displaying
     * "mealSuggest.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/mealSuggest")
    public String mealSuggest() {
        return "mealSuggest";
    }

    /**
     * Handles POST requests from "mealSuggest.html". If the "Return to Home page"
     * button is pressed, redirects the user to "home.html".
     * 
     * @param submitFormButton the result of the button press from
     *                         "mealSuggest.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/mealSuggest")
    public String mealSuggest(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/mealSuggest";
    }
}
