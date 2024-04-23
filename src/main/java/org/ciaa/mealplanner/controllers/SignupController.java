package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the sign up page
 * of the application, "signup.html".
 */
@Controller
public class SignupController
{

    /**
     * Handles GET requests from "signup.html". Responsible for displaying
     * "signup.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    /**
     * Handles POST requests from "signup.html" by redirecting the user to either
     * "index.html" or "signup.html" (this same page) depending on which button in
     * "index.html" was pressed.
     * 
     * A "User" object is automatically serialized from the input of "signup.html",
     * and the data of this new User object is written to a new line of the text
     * file via the 'addNewUser()' static method of "Control.java".
     * 
     * If the new User object was unable to be created, it redirects to the same
     * signup page, essentially refreshing it. Otherwise, it redirects to the
     * landing page where the user can log into their newly created account with the
     * credentials they just created.
     * 
     * @param user             the automatically generated User object representing
     *                         the new user signing up.
     * @param submitFormButton the result of the button press from "signup.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, @RequestParam("submitFormButton") String submitFormButton) {

        // if the User object was not created, don't go anywhere
        if (user == null) {
            return "redirect:/signup";
        }

        // add the new user to the system
        Control.addNewUser(user);

        // go to login page after signing up
        return "redirect:/index";
    }
}
