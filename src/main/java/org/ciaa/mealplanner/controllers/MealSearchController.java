package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

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
}
