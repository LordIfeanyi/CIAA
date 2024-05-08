package org.ciaa.mealplanner.controllers;

import java.util.List;
import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.UpdateUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The controller class responsible for handling requests from the saved meals
 * page of the application, "savedMeals.html".
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-03-28
 */
@Controller
public class SavedMealsController {

    /**
     * Handles GET requests from "savedMeals.html".
     * Responsible for displaying "savedMeals.html".
     * 
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/savedMeals")
    public String savedMeals() {
        return Control.getCurrentUser() == null ? "redirect:/index" : "savedMeals";
    }

    /**
     * Handles GET requests from "savedMeals.html" to the endpoint
     * "ciaa/saved-meals/intolerances" to get the current user's saved meals.
     * The saved meals are in the form of spoonacular recipe IDs.
     * 
     * @return The current user's saved meals in the form of spoonacular recipe IDs
     */
    @GetMapping("/ciaa/saved-meals")
    @ResponseBody
    public List<String> getSavedMeals() {

        return Control.getCurrentUser().getMeals();
    }

    /**
     * Handles POST requests from "mealSearch.html" and "mealSuggest.html" to the
     * endpoint "/ciaa/saved-meals/{id}" to save meals to the current user's account.
     * The meal to be saved is passed as its spoonacular recipe id number,
     * and is stored in the text file in the current user's line.
     *
     * @param id The meal's spoonacular recipe id
     * @return The status of the request
     */
    @PostMapping("/ciaa/saved-meals/{id}")
    public ResponseEntity<?> saveMeal(@PathVariable String id) {

        // save the item
        UpdateUserInfo updateUserInfo = new UpdateUserInfo();
        updateUserInfo.setNewMeal(id);
        Control.updateUserInfo(updateUserInfo);

        return ResponseEntity.ok().build();
    }

    /**
     * Handles DELETE requests from "savedMeals.html" to the endpoint "/ciaa/saved-meals/{id}"
     * to delete the given meal from the current user's saved meals.
     * The 'id' field passes the meal to be deleted in the
     * endpoint, which is the meal's corresponding spoonacular id.
     * 
     * @param id The spoonacular recipe id of the meal to be deleted from the
     *           current user's saved meals.
     * @return The status of the request
     */
    @DeleteMapping("/ciaa/saved-meals/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable String id) {

        try {
            // delete the item
            UpdateUserInfo updateUserInfo = new UpdateUserInfo();
            updateUserInfo.setRemoveMeal(id);
            Control.updateUserInfo(updateUserInfo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete item");
        }
    }
}
