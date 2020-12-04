package com.zrana.springit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


//@RestController = @ResponseBody + @Controller
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title","Hello, thymeleaf");
        return "home";
    }
}
