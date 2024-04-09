package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller class responsible for handling requests from the home page
 * of the application, "homePage.html".
 */
@Controller
public class HomePageController {

    /**
     * Handles GET requests from "homePage.html". Responsible for displaying
     * "homePage.html".
     * 
     * @return the name of the html file to be displayed.
     */
    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }

    // /**
    //  * Handles POST requests from "home.html" by redirecting the user to either
    //  * "userInfo.html", "savedMeals.html", "mealSearch.html", or "mealSuggest.html"
    //  * depending on which button in "home.html" is pressed.
    //  * 
    //  * @param submitFormButton the result of the button press from "index.html".
    //  * @return the name of the html file to be displayed.
    //  */
    // @PostMapping("/home")
    // public String home(@RequestParam("submitFormButton") String submitFormButton) {

    //     if (submitFormButton.equals("userInfo")) {
    //         return "redirect:/userInfo";
    //     } else if (submitFormButton.equals("savedMeals")) {
    //         return "redirect:/savedMeals";
    //     } else if (submitFormButton.equals("mealSearch")) {
    //         return "redirect:/mealSearch";
    //     } else if (submitFormButton.equals("mealSuggest")) {
    //         return "redirect:/mealSuggest";
    //     } else {
    //         return "redirect:/home";
    //     }
    // }
}
