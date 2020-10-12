package com.air.careerassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
@GetMapping("/")
    public String showhome(){
    return "home";
}

@PostMapping("/jobsearch")
    public RedirectView showSearches(String title, String location){
        System.out.println("Inside /job searches here with title "+title+" "+location);
        return new RedirectView("/");
}
}

