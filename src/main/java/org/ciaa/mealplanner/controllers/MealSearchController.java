package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.ListResponse;
import org.ciaa.mealplanner.types.SearchMealsRequest;
import org.ciaa.mealplanner.types.SimpleRecipe;
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
 * search page of the application, "mealSearch.html".
 *
 * @author Andrew Mazlumyan
 * @author C. Becerra
 * <p>
 * Created on 2024-03-28
 */
@Controller
public class MealSearchController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MealSearchController.class);
    private final ApiHandler apiHandler;

    @Autowired
    public MealSearchController(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    /**
     * Handles GET requests from "mealSearch.html".
     * Responsible for displaying "mealSearch.html".
     *
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/mealSearch")
    public String mealSearch() {
        return Control.getCurrentUser() == null ? "redirect:/index" : "mealSearch";
    }

    @PostMapping("/ciaa/meal-search/search")
    @ResponseBody
    public ListResponse<SimpleRecipe> searchMeals(@RequestBody SearchMealsRequest request) {
        ListResponse<SimpleRecipe> recipesResponse = apiHandler.searchMeals(request);
        LOGGER.debug("Response from searchMeals: {}", recipesResponse.toString());
        return recipesResponse;
    }
}
