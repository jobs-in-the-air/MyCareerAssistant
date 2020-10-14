package com.air.careerassistant.controller;

import com.air.careerassistant.api.AdzunaJobs;
import com.air.careerassistant.api.GitHubJobs;
import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class HomeController {
    private ArrayList<Job> listOfGitHubJobs = new ArrayList<>();
    private HashMap<Integer, Boolean> viewJobObject;
    private Integer jobILike = -1;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String showhome(Model m){
        m.addAttribute("jobList", listOfGitHubJobs);
        m.addAttribute("jobILike", jobILike);
        m.addAttribute("viewJobObject", viewJobObject);
        return "home";
}

@PostMapping("/jobsearch")
    public RedirectView showSearches(String title, String location, Model m) throws IOException {
        System.out.println("Inside /job searches here with title "+title+" "+location);
        // api call here
        //AdzunaJobs.getAdzunaJobs(title,location);
        listOfGitHubJobs = GitHubJobs.getGitHubJobs(title,location);
        viewJobObject = new HashMap<>();
        ApplicationUser testUser = new ApplicationUser();
        JobStatus test = new JobStatus();
        Job testJob = new Job(testUser, "test","test","test","test","test","test","test",test);
        listOfGitHubJobs.add(testJob);
        for (Integer i=0; i< listOfGitHubJobs.size(); i++){
            viewJobObject.put(i, false);
        }
        return new RedirectView("/");
}

    @PostMapping("/jobdetail")
    public RedirectView viewJob(String choice, Integer jobIndex){
        jobILike = jobIndex;
        if(choice.equals("view")){
            viewJobObject.replace(jobILike,true);
        } else {
            viewJobObject.replace(jobILike,false);
        }
        if (viewJobObject.size()>0){
        }
        return new RedirectView("/");
    }

    @PostMapping("/saveJobFromApi")
    public RedirectView saveJobFromApi(int jobIndex, Model m, Principal principal){
        Job newJobFromGitHub = listOfGitHubJobs.get(jobIndex);
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        JobStatus newJobStatus = new JobStatus();
        newJobFromGitHub.setType("null");
        newJobFromGitHub.setApplicationUser(currentUser);
        newJobFromGitHub.setJobStatus(newJobStatus);
        newJobFromGitHub.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
        newJobFromGitHub.setDescription("null");
        jobRepository.save(listOfGitHubJobs.get(jobIndex));
        return new RedirectView("/allmyjobs");
    }

}

