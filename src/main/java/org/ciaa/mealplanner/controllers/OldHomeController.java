package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.UpdateUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the home page
 * of the application, "home.html".
 */
@Controller
public class OldHomeController {

    /**
     * Handles GET requests from "home.html". Responsible for displaying
     * "home.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/oldHome")
    public String oldHome() {
        return "oldHome";
    }

    /**
     * Handles POST requests from "home.html" by redirecting the user to either
     * "userInfo.html", "savedMeals.html", "mealSearch.html", or "mealSuggest.html"
     * depending on which button in "home.html" is pressed.
     * 
     * @param submitFormButton the result of the button press from "index.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/oldHome")
    public String oldHome(@RequestParam("submitFormButton") String submitFormButton) {

        if (submitFormButton.equals("userInfo")) {
            return "redirect:/userInfo";
        } else if (submitFormButton.equals("savedMeals")) {
            return "redirect:/savedMeals";
        } else if (submitFormButton.equals("mealSearch")) {
            return "redirect:/mealSearch";
        } else if (submitFormButton.equals("mealSuggest")) {
            return "redirect:/mealSuggest";
        } else if (submitFormButton.equals("changeFirstName")) {
            
            /*debug*/ System.out.println("changing the first name");

            UpdateUserInfo updateUserInfo = new UpdateUserInfo();
            updateUserInfo.setFirstName("Bob");

            Control.updateUserInfo(updateUserInfo);

            return "redirect:/oldHome";
        } else {
            return "redirect:/oldHome";
        }
    }
}
