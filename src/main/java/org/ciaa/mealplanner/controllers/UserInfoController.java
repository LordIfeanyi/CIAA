package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.UpdateUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

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
     * @param submitFormButton the result of the button press from
     *                         "userInfo.html".
     * @return the name of the html file to be displayed.
     */
    @PostMapping("/userInfo")
    public String userInfo(@ModelAttribute UpdateUserInfo updatedInfo,
            @RequestParam("submitFormButton") String submitFormButton, HttpSession session) {

        if (submitFormButton.equals("cancel")) {
            return "redirect:/homePage";
        }

        if (submitFormButton.equals("clearIntolerances")) {
            updatedInfo.setClearIntolerances(true);
        }

        Control.updateUserInfo(updatedInfo);

        session.setAttribute("firstName", Control.getCurrentUser().getFirstName());
        session.setAttribute("lastName", Control.getCurrentUser().getLastName());
        session.setAttribute("email", Control.getCurrentUser().getEmail());
        session.setAttribute("username", Control.getCurrentUser().getUsername());
        session.setAttribute("password", Control.getCurrentUser().getPassword());
        session.setAttribute("intolerances", Control.getCurrentUser().getIntolerances().toString());

        return "redirect:/userInfo";
    }
}
