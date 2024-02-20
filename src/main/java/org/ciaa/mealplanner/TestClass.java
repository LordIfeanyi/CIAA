package org.ciaa.mealplanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass
{
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
