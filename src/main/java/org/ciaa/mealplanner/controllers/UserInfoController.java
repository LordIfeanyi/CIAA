package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserInfoController {
    
    @GetMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
    }

    @PostMapping("/userInfo")
    public String userInfo(@RequestParam("submitFormButton") String submitFormButton) {

        // if the 'Return to Home page' button was selected
        if (submitFormButton.equals("home")) {
            return "redirect:/home";
        }

        return "redirect:/userInfo";
    }
}
