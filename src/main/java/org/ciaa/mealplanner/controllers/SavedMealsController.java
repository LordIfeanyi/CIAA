package org.ciaa.mealplanner.controllers;

import java.util.List;
import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.UpdateUserInfo;
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
 */
@Controller
public class SavedMealsController {

    /**
     * Handles GET requests from "savedMeals.html". Responsible for displaying
     * "savedMeals.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/savedMeals")
    public String savedMeals() {
        return "savedMeals";
    }

    // /**
    // * Handles POST requests from "savedMeals.html". If the "Return to Home page"
    // * button is pressed, redirects the user to "home.html".
    // *
    // * @param submitFormButton the result of the button press from
    // * "savedMeals.html".
    // * @return the name of the html file to be displayed.
    // */
    // @PostMapping("/savedMeals")
    // public String savedMeals(@RequestParam("submitFormButton") String
    // submitFormButton) {

    // // if the 'Return to Home page' button was selected
    // if (submitFormButton.equals("home")) {
    // return "redirect:/homePage";
    // }

    // return "redirect:/savedMeals";
    // }

    // Returns the current user's saved meals. These are in the form of recipe IDs.
    @GetMapping("/ciaa/savedmeals")
    @ResponseBody
    public List<String> getSavedMeals() {

        // /* debug */ System.out.println("inside '/ciaa/savedmeals' GET method");
        // /* debug */ System.out.println("Returning: " +
        // Control.getCurrentUser().getMeals().toString());

        return Control.getCurrentUser().getMeals();
    }

    // Deletes the corresponding meal from the current user's saved meals.
    @DeleteMapping("/ciaa/savedmeals/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable String id) {

        /* debug */ System.out.println("inside '/ciaa/savedmeals/{" + id + "}' DELETE method");

        try {

            /* debug */System.out.println("trying to delete meal " + id);

            /* debug */System.out.println("current user's saved meals before deletion of " + id + ": " + Control.getCurrentUser().getMeals().toString());

            // delete the item
            UpdateUserInfo updateUserInfo = new UpdateUserInfo();
            updateUserInfo.setRemoveMeal(id);
            Control.updateUserInfo(updateUserInfo);

            /* debug */System.out.println("current user's saved meals after deletion of " + id + ": " + Control.getCurrentUser().getMeals().toString());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete item");
        }
    }
}



