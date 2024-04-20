package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.UpdateUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The controller class responsible for handling requests from the meal
 * suggester page of the application, "mealSuggest.html".
 */
@Controller
public class MealSearchController {

    /**
     * Handles GET requests from "mealSuggest.html". Responsible for displaying
     * "mealSuggest.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/mealSearch")
    public String mealSearch() {
        return "mealSearch";
    }

    // /**
    // * This method is called when the javascript in "mealSearch.html" makes a GET
    // * request to the endpoint "/search". It takes the search parameters from the
    // * user, makes a call to the meal API according to those parameters, and
    // returns
    // * the results in JSON form.
    // *
    // * @param query
    // * @param cuisine
    // * @param diet
    // * @param intolerances
    // * @return
    // */
    // @GetMapping("/search")
    // @ResponseBody
    // public String search(@RequestParam String query,
    // @RequestParam(required = false) String cuisine,
    // @RequestParam(required = false) String diet,
    // @RequestParam(required = false) List<String> intolerances) {

    // /* print statements for debugging */
    // if (query != null) {
    // System.out.println(query.toString());
    // }
    // if (cuisine != null) {
    // System.out.println(cuisine.toString());
    // }
    // if (diet != null) {
    // System.out.println(diet.toString());
    // }
    // if (intolerances != null) {
    // System.out.println(intolerances.toString());
    // }

    // // TODO: Make the API request with the parameters and return the response.

    // // Dummy example; returning static search results
    // List<String> results = Arrays.asList("Result 1", "Result 2", "Result 3");
    // return new Gson().toJson(results);
    // }

    @PostMapping("ciaa/savedmeals/{id}")
    public ResponseEntity<?> saveMeal(@PathVariable String id) {

        /* debug */ System.out.println("saving the meal " + id);

        /* debug */System.out.println("current user's saved meals before saving " + id + ": "
                + Control.getCurrentUser().getMeals().toString());

        // save the item
        UpdateUserInfo updateUserInfo = new UpdateUserInfo();
        updateUserInfo.setNewMeal(id);
        Control.updateUserInfo(updateUserInfo);

        /* debug */System.out.println("current user's saved meals after saving " + id + ": "
                + Control.getCurrentUser().getMeals().toString());

        return ResponseEntity.ok().build();
    }
}
