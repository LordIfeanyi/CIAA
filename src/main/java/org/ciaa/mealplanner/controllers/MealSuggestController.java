package org.ciaa.mealplanner.controllers;

import java.util.List;

import com.spoonacular.client.model.GetRandomRecipes200Response;
import org.ciaa.mealplanner.CiaaApplication;
import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.User;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Handles GET requests from "mealSuggest.html" to the endpoint
     * "ciaa/savedmeals/intolerances" to get the current user's intolerances.
     * 
     * @return the current user's intolerances
     */
    @GetMapping("/ciaa/mealsuggest/intolerances")
    @ResponseBody
    public List<String> getIntolerances() {

        return Control.getCurrentUser().getIntolerances();
    }

    @PostMapping("/ciaa/mealsuggest/suggest")
    @ResponseBody
    public GetRandomRecipes200Response suggestMeals(@RequestBody String type) {
        User currentUser = Control.getCurrentUser();
        CiaaApplication.LOGGER.debug("Suggesting meals for user: " + currentUser.getUsername() + " of type: " + type);
        return ApiHandler.suggestMeals(currentUser, type);
    }
}
