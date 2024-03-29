package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the start page of
 * the application, "index.html".
 */
@Controller
public class IndexController {

    /**
     * Handles GET requests from "index.html". Responsible for displaying
     * "index.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * Handles POST requests from "index.html" by redirecting the user to either
     * "register.html" or "login.html" depending on which button in "index.html" was
     * pressed.
     * 
     * @param submitFormButton the result of the button press from "index.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/start")
    public String start(@RequestParam("submitFormButton") String submitFormButton) {

        if (submitFormButton.equals("register")) {
            return "redirect:/register";
        } else if (submitFormButton.equals("login")) {
            return "redirect:/login";
        } else {
            return "redirect:/index";
        }
    }
}
