package org.ciaa.mealplanner.controllers;

import org.ciaa.mealplanner.Control;
import org.ciaa.mealplanner.UserSignIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

/**
 * The controller class responsible for handling requests from the landing page
 * of the application, "index.html".
 */
@Controller
public class IndexController {

    /**
     * Handles GET requests from "index.html".
     * Responsible for displaying "index.html".
     *
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * Handles POST requests from "index.html" by redirecting the user to either "register.html"
     * or "login.html" depending on which button in "index.html" was pressed.
     * <p>
     * A "UserSignIn" object is automatically serialized from the input of "index.html".
     * The data in this object is then used to verify the login attempt via
     * the 'authenticateUser()' static method of "Control.java".
     * If the login is authenticated, a new "User" objected is created
     * with the data of the text file line which corresponded to the login attempt.
     * This new User object is then set as the 'currentUser' field of "Control.java".
     * <p>
     * An instance of HttpSession is also automatically created,
     * and fields are created that correspond to the current user's data.
     * These fields are used to be displayed elsewhere in the application.
     *
     * @param userSignIn       The automatically generated UserSignIn object used
     *                         for login verification.
     * @param submitFormButton The result of the button press from "index.html".
     * @param session          The automatically generated HttpSession object.
     * @return The name of the html file to be displayed.
     */
    @PostMapping("/index")
    public String index(@ModelAttribute UserSignIn userSignIn,
            @RequestParam("submitFormButton") String submitFormButton, HttpSession session) {

        if (submitFormButton.equals("login")) {

            boolean authenticated = Control.authenticateUser(userSignIn);

            if (authenticated) {

                session.setAttribute("firstName", Control.getCurrentUser().getFirstName());
                session.setAttribute("lastName", Control.getCurrentUser().getLastName());
                session.setAttribute("email", Control.getCurrentUser().getEmail());
                session.setAttribute("username", Control.getCurrentUser().getUsername());
                session.setAttribute("password", Control.getCurrentUser().getPassword());
                session.setAttribute("intolerances", Control.getCurrentUser().getIntolerances().toString());

                return "redirect:/homePage"; // enter the home page
            }
        }
        return "redirect:/index";
    }
}
