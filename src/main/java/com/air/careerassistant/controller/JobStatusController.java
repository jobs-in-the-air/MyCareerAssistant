package com.air.careerassistant.controller;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.jobTrack.JobStatus;
import com.air.careerassistant.model.jobTrack.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;

@Controller
public class JobStatusController {

    @Autowired
    JobStatusRepository jobStatusRepository;

    @Autowired
    JobRepository jobRepository;

    @PostMapping("/status")
    public RedirectView updateJobStatus(Principal principal, String status, Long jobStatusId) {
        JobStatus jobStatus = jobStatusRepository.getOne(jobStatusId);
        jobStatus.setStatus(status);
        Job job = jobRepository.getOne(jobStatus.getJob().getId());
        job.setLastUpdated(new Date(Calendar.getInstance().getTime().getTime()));
        jobRepository.save(job);
        jobStatusRepository.save(jobStatus);
        return new RedirectView("/jobdetails/" + jobStatus.getJob().getId());
    }
}
