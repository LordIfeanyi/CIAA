package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.UpdateUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The controller class responsible for handling requests from the meal
 * search page of the application, "mealSearch.html".
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-03-28
 */
@Controller
public class MealSearchController {

    /**
     * Handles GET requests from "mealSearch.html".
     * Responsible for displaying "mealSearch.html".
     * 
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/mealSearch")
    public String mealSearch() {
        return "mealSearch";
    }

    /**
     * Handles POST requests from "mealSearch.html" and "mealSuggest.html" to the
     * endpoint "ciaa/savedmeals/{id}" to save meals to the current user's account.
     * The meal to be saved is passed as its spoonacular recipe id number,
     * and is stored in the text file in the current user's line.
     * 
     * @param id The meal's spoonacular recipe id
     * @return The status of the request
     */
    @PostMapping("ciaa/savedmeals/{id}")
    public ResponseEntity<?> saveMeal(@PathVariable String id) {

        // save the item
        UpdateUserInfo updateUserInfo = new UpdateUserInfo();
        updateUserInfo.setNewMeal(id);
        Control.updateUserInfo(updateUserInfo);

        return ResponseEntity.ok().build();
    }
}
