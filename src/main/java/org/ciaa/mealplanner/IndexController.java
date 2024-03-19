package org.ciaa.mealplanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("/start")
    public String start(@ModelAttribute Choice choice) {

        System.out.println("Choice = " + choice.getChoice());

        if(choice.getChoice().equals("register")) {
            return "register";
        } else if (choice.getChoice().equals("login")) {
            return "login";
        } else {
            return "index";
        }
    }
}
