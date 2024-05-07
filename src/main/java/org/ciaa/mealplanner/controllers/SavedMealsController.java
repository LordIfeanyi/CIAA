package org.ciaa.mealplanner.controllers;

import java.util.List;
import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.UpdateUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "savedMeals";
    }

    /**
     * Handles GET requests from "savedMeals.html" to the endpoint
     * "ciaa/savedmeals/intolerances" to get the current user's saved meals.
     * The saved meals are in the form of spoonacular recipe IDs.
     * 
     * @return The current user's saved meals in the form of spoonacular recipe IDs
     */
    @GetMapping("/ciaa/savedmeals")
    @ResponseBody
    public List<String> getSavedMeals() {

        return Control.getCurrentUser().getMeals();
    }

    /**
     * Handles DELETE requests from "savedMeals.html" to the endpoint "/ciaa/savedmeals/{id}"
     * to delete the given meal from the current user's saved meals.
     * The 'id' field passes the meal to be deleted in the
     * endpoint, which is the meal's corresponding spoonacular id.
     * 
     * @param id The spoonacular recipe id of the meal to be deleted from the
     *           current user's saved meals.
     * @return The status of the request
     */
    @DeleteMapping("/ciaa/savedmeals/{id}")
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
