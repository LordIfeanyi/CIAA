package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Start page' button was selected
        if (submitFormButton.equals("start")) {
            return "redirect:/index";
        }

        // if the User object was not created, don't go anywhere
        if (user == null) {
            return "redirect:/register";
        }

        /* debug */
        // System.out.println(user.toString());

        Control.addUser(user);

        /* debug */
        // Control.printUsers();

        // go to login page after registering
        return "redirect:/login";

    }

}
