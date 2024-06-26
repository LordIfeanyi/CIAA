package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The controller class responsible for handling requests from the home page
 * of the application, "homePage.html".
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-04-09
 */
@Controller
public class HomePageController {

    /**
     * Handles GET requests from "homePage.html".
     * Responsible for displaying "homePage.html".
     * 
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/homePage")
    public String homePage() {
        return Control.getCurrentUser() == null ? "redirect:/index" : "homePage";
    }
}
