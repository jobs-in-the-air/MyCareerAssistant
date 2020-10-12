package com.air.careerassistant.controller;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/jobdetails")
    public String showDetails() {
        return "details";
    }

    @PostMapping("/jobsearchresult")
    public RedirectView saveDetails(
            Principal principal,
            String jobUrl,
            String company,
            String company_url,
            String title,
            String location,
            String description,
            String type
    ) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        Job newJob = new Job(currentUser, jobUrl, company, company_url, title, location, description, type);
        jobRepository.save(newJob);
        return new RedirectView("/jobdetails");
    }

    @GetMapping("/jobdetails/{localId}")
    public String showNewJobDetails(Model model, Principal principal, @PathVariable Long localId) {
        Job job = jobRepository.getOne(localId);
        if (job.getApplicationUser().getUsername().equals(principal.getName())) {
            model.addAttribute("currentJob", job);
        }
        return "details";
    }
}
