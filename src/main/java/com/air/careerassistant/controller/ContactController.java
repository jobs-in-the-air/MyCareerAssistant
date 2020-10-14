package com.air.careerassistant.controller;

import com.air.careerassistant.model.contact.Contact;
import com.air.careerassistant.model.contact.ContactRepository;
import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.job.JobRepository;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller

public class ContactController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    JobRepository jobRepository;

 @GetMapping("/addcontacts")
 public String showcontacts(Principal principal, Model m) {
     if (principal != null){
         m.addAttribute("principal",principal);
     }
     return "addcontacts";
 }
 @PostMapping("/addcontacts")
    public RedirectView addcontacts(Principal principal, String name, String linkedin){
     ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
     Contact contact = new Contact(linkedin, name, user);
     contactRepository.save(contact);
     return new RedirectView("/addcontacts");

 }
 @PostMapping("/updatejobcontacts")
    public RedirectView updjobatecontacts(Principal principal, Long jobId, Long contactId) {
     Contact contact = contactRepository.getOne(contactId);
     Job job = jobRepository.getOne(jobId);
     contact.addJob(job);
     job.addContact(contact);
     contactRepository.save(contact);
     jobRepository.save(job);
     return new RedirectView("/jobdetails/" + jobId);
 }

}
