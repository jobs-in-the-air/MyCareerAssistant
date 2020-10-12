package com.air.careerassistant.controller;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.post.Post;
import com.air.careerassistant.model.post.PostRepository;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    PostRepository postRepository;
    @PostMapping("/post")
    public RedirectView addPost(String body, Long jobId, Principal principal){
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        Job job = jobRepository.getOne(jobId);
        Post post = new Post(job, user, body);
        postRepository.save(post);
        return new RedirectView("/jobdetails/"+jobId);
    }
}
