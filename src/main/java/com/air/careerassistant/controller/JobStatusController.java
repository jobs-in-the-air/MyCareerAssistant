package com.air.careerassistant.controller;

import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.jobTrack.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class JobStatusController {

    @Autowired
    JobStatusRepository jobStatusRepository;

    @PostMapping("/status")
    public RedirectView updateJobStatus(Principal principal, String status, Long jobStatusId) {
        JobStatus jobStatus = jobStatusRepository.getOne(jobStatusId);
        jobStatus.setStatus(status);
        jobStatusRepository.save(jobStatus);
        return new RedirectView("/jobdetails/" + jobStatus.getJob().getId());
    }
}
