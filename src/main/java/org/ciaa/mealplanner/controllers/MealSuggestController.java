package org.ciaa.mealplanner.controllers;

import java.util.List;

import com.spoonacular.client.model.GetRandomRecipes200Response;
import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // Returns the current user's intolerances.
    @GetMapping("/ciaa/mealsuggest/intolerances")
    @ResponseBody
    public List<String> getIntolerances() {

        //* debug */ System.out.println("inside getIntolerances \n Returning this: " + Control.getCurrentUser().getIntolerances().toString());

        return Control.getCurrentUser().getIntolerances();
    }

    @GetMapping("/ciaa/mealsuggest/suggest")
    @ResponseBody
    public GetRandomRecipes200Response suggestMeals() {
        return ApiHandler.suggestMeals(Control.getCurrentUser());
    }
}
