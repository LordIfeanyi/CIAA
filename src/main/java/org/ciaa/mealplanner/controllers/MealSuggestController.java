package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.RecipesResponse;
import org.ciaa.mealplanner.types.User;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The controller class responsible for handling requests from the meal
 * suggester page of the application, "mealSuggest.html".
 *
 * @author C. Becerra
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-03-28
 */
@Controller
public class MealSuggestController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MealSuggestController.class);

    @Autowired
    private ApiHandler apiHandler;

    /**
     * Handles GET requests from "mealSuggest.html".
     * Responsible for displaying "mealSuggest.html".
     *
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/mealSuggest")
    public String mealSuggest() {
        return "mealSuggest";
    }

    /**
     * Handles POST requests from "mealSuggest.html"
     * to the endpoint "ciaa/mealsuggest/suggest".
     * This method is responsible for suggesting meals
     * based on the meal type provided in the request body.
     *
     * @param mealType The type of meal to suggest.
     *                 This is a string value provided in the request body.
     * @return The response from the Spoonacular API which contains a list of suggested meals.
     */
    @PostMapping("/ciaa/meal-suggest/suggest")
    @ResponseBody
    public RecipesResponse suggestMeals(@RequestBody String mealType) {
        User currentUser = Control.getCurrentUser();
        LOGGER.debug("Suggesting meals for user: {} of mealType: {}", currentUser.getUsername(), mealType);
        return apiHandler.suggestMeals(currentUser, mealType);
    }
}
