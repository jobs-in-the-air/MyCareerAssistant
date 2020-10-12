package com.air.careerassistant.controller;

import com.air.careerassistant.api.AdzunaJobs;
import com.air.careerassistant.api.GitHubJobs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class HomeController {
@GetMapping("/")
    public String showhome(){
    return "home";
}

@PostMapping("/jobsearch")
    public RedirectView showSearches(String title, String location) throws IOException {
        System.out.println("Inside /job searches here with title "+title+" "+location);
        // api call here
        // AdzunaJobs.getAdzunaJobs(title,location);
        GitHubJobs.getGitHubJobs(title,location);
        // iterate through the json object and then run it through the constructor

        return new RedirectView("/");
}
}

