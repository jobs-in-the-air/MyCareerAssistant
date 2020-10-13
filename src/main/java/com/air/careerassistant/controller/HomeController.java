package com.air.careerassistant.controller;

import com.air.careerassistant.api.AdzunaJobs;
import com.air.careerassistant.api.GitHubJobs;
import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.user.ApplicationUser;
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
    private Boolean viewdetail=false;

    @GetMapping("/")
    public String showhome(Model m){
        m.addAttribute("jobList", listOfGitHubJobs);
        m.addAttribute("viewdetail", viewdetail);
    return "home";
}

@PostMapping("/jobsearch")
    public RedirectView showSearches(String title, String location, Model m) throws IOException {
        System.out.println("Inside /job searches here with title "+title+" "+location);
        // api call here
        //AdzunaJobs.getAdzunaJobs(title,location);
        listOfGitHubJobs = GitHubJobs.getGitHubJobs(title,location);
        ApplicationUser testUser = new ApplicationUser();
        JobStatus test = new JobStatus();
        Job testJob = new Job(testUser, "test","test","test","test","test","test","test",test);
        listOfGitHubJobs.add(testJob);
        // iterate through the json object and then run it through the constructor
        return new RedirectView("/");
}

    @PostMapping("/jobdetail")
    public RedirectView viewJob(Model m, String choice){
        if(choice.equals("view")){
            viewdetail = true;
        } else {
            viewdetail = false;
        }
        m.addAttribute("jobList", listOfGitHubJobs);
        m.addAttribute("viewdetail", viewdetail);
        return new RedirectView("/");
    }



}

