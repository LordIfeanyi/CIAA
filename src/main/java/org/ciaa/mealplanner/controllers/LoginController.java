package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.models.User;
import org.ciaa.mealplanner.models.UserSignIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserSignIn userSignIn,
            @RequestParam("submitFormButton") String submitFormButton, HttpSession session) {

        // if the 'Return to Start page' button was selected
        if (submitFormButton.equals("start")) {
            return "redirect:/index";
        } else {

            User user = Control.checkIfExists(userSignIn);

            // if the UserSignIn is authenticated
            if (user != null) {

                /* debug */
                //System.out.println("user.getFirstName() = " + user.getFirstName());

                session.setAttribute("firstName", user.getFirstName());

                /* debug */
                // System.out.println("session.getAttribute(ÍfirstName) = " + session.getAttribute("firstName"));

                return "redirect:/home"; // enter the home page
            } else {
                return "redirect:/login"; // reload the login page
            }
        }
    }

}
