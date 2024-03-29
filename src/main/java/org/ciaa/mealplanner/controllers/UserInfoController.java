package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the user info
 * page of the application, "userInfo.html".
 */
@Controller
public class UserInfoController {

    /**
     * Handles GET requests from "userInfo.html". Responsible for displaying
     * "userInfo.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
    }

    /**
     * Handles POST requests from "userInfo.html". If the "Return to Home page"
     * button is pressed, redirects the user to "home.html".
     * 
     * @param submitFormButton the result of the button press from "userInfo.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/userInfo")
    public String userInfo(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/userInfo";
    }
}
