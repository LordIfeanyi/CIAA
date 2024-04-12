package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the home page
 * of the application, "homePage.html".
 */
@Controller
public class HomePageController {

    /**
     * Handles GET requests from "homePage.html". Responsible for displaying
     * "homePage.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }
}
