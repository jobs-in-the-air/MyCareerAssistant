package com.air.careerassistant.controller;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.jobTrack.JobStatusRepository;
import com.air.careerassistant.model.user.ApplicationUser;
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
    JobStatusRepository jobStatusRepository;

    @GetMapping("/jobdetails")
    public String showDetails() {
        return "details";
    }

    @PostMapping("/jobsearchresult")
    public RedirectView saveDetails(
            String jobUrl,
            String company,
            String company_url,
            String title,
            String location,
            String description,
            String type
    ) {
        JobStatus newJobStatus = new JobStatus();
        jobStatusRepository.save(newJobStatus);
        Job newJob = new Job(jobUrl, company, company_url, title, location, description, type, newJobStatus);
        jobRepository.save(newJob);
        return new RedirectView("/jobdetails");
    }

    @GetMapping("/jobdetails/{localId}")
    public String showNewJobDetails(@PathVariable Long localId) {

        return "details";
    }


}
