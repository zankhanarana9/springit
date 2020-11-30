package com.zrana.springit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController = @ResponseBody + @Controller
@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Hello, Spring boot 2";
    }
}
