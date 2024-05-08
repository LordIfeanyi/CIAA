package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.types.Recipe;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecipeInformationController
{
    private final ApiHandler apiHandler;

    @Autowired
    public RecipeInformationController(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    @GetMapping("/ciaa/recipe-information/{id}")
    @ResponseBody
    public Recipe getRecipeInformation(@PathVariable String id) {
        return apiHandler.getRecipeInformation(id);
    }
}
