package com.air.careerassistant.controller;

import com.air.careerassistant.api.AdzunaJobs;
import com.air.careerassistant.api.GitHubJobs;
import com.air.careerassistant.model.job.Job;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {
    private ArrayList<Job> listOfGitHubJobs = new ArrayList<>();

    @GetMapping("/")
    public String showhome(Model m){
        m.addAttribute("jobList", listOfGitHubJobs);

    return "home";
}

@PostMapping("/jobsearch")
    public RedirectView showSearches(String title, String location, Model m) throws IOException {
        System.out.println("Inside /job searches here with title "+title+" "+location);
        // api call here
        //AdzunaJobs.getAdzunaJobs(title,location);
        listOfGitHubJobs = GitHubJobs.getGitHubJobs(title,location);
    System.out.println("This is list of GitHub jobs" + listOfGitHubJobs);
        // iterate through the json object and then run it through the constructor
        return new RedirectView("/");
}


}

