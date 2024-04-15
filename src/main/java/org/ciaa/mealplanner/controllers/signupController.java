package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the register page
 * of the application, "register.html".
 */
@Controller
public class signupController {

    /**
     * Handles GET requests from "register.html". Responsible for displaying
     * "register.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * Handles POST requests from "register.html". Redirects the user to
     * "index.html" if the "Return to Start page" button is pressed, or
     * "register.html" (this same page) if unable to create a new User. Creates a
     * new User object with the data entered by the user into the register page and
     * adds it to the list of User objects, "users", in "Control.java".
     * 
     * @param user             the User object automatically created by the
     *                         "@ModelAttribute" SpringBoot annotation from the data
     *                         from the html form.
     * @param submitFormButton the result of the button press from "register.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, @RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Start page' button was selected
        // if (submitFormButton.equals("start")) {
        //     return "redirect:/index";
        // }

        // if the User object was not created, don't go anywhere
        if (user == null) {
            return "redirect:/signup";
        }

        // add the new user to the list of users in Control
        Control.addNewUser(user);

        // go to login page after registering
        return "redirect:/index";
    }
}
