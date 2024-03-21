package org.ciaa.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/start")
    public String start(@RequestParam("submitFormButton") String submitFormbutton) {

        if(submitFormbutton.equals("register")) {
            return "redirect:/register";
        } else if(submitFormbutton.equals("login")) {
            return "redirect:/login";
        } else {
            return "redirect:/index";
        }
    }
}
