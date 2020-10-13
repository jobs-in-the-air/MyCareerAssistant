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

@Controller
public class HomeController {
    private ArrayList<Job> listOfGitHubJobs = new ArrayList<>();
    private Boolean viewdetail=false;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

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

    @PostMapping("/saveJobFromApi")

    public RedirectView saveJobFromApi(int jobIndex, Model m, Principal principal){
        System.out.println("this is job info after saving" + jobIndex);
        System.out.println("list of gitHub jobs " + listOfGitHubJobs.get(jobIndex).getClass());
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

//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//    public void setType(String type) {
//        this.type = type;
//    }
//    public void setJobStatus(JobStatus jobStatus) {
//        this.jobStatus = jobStatus;
//    }
//    public void setApplicationUser(ApplicationUser applicationUser) {
//        this.applicationUser = applicationUser;
//    }

}

