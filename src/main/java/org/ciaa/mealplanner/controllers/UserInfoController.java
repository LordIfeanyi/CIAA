package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.types.UpdateUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

/**
 * The controller class responsible for handling requests from the user info
 * page of the application, "userInfo.html".
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-03-28
 */
@Controller
public class UserInfoController {

    /**
     * Handles GET requests from "userInfo.html".
     * Responsible for displaying "userInfo.html".
     * 
     * @return The name of the html file to be displayed.
     */
    @GetMapping("/userInfo")
    public String userInfo() {
        return Control.getCurrentUser() == null ? "redirect:/index" : "userInfo";
    }

    /**
     * Handles POST requests from "userInfo.html".
     * Redirects to "homePage.html" if the cancel button is pressed,
     * or "userInfo.html" (this same page) if any other button is pressed.
     * <p>
     * A "UpdateUserInfo" object is automatically
     * serialized from the input of "userInfo.html".
     * The data in this object is then used to
     * update the data of the current user.
     * <p>
     * An instance of HttpSession is also automatically created,
     * and fields are created that correspond to the current user's data.
     * The attributes of this session are used to display the
     * current state of the user's data on the "userInfo.html" page.
     * 
     * @param updatedInfo      The automatically generated UpdateUserInfo object
     * @param submitFormButton The result of the button press from "userInfo.html".
     * @param session          The automatically generated HttpSession object.
     * @return The name of the html file to be displayed.
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
