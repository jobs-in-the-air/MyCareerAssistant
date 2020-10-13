package com.air.careerassistant.controller;

import com.air.careerassistant.model.contact.Contact;
import com.air.careerassistant.model.contact.ContactRepository;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 @GetMapping("/addcontacts")
 public String showcontacts() {
     return "addcontacts";
 }
 @PostMapping("/addcontacts")
    public RedirectView addcontacts(Principal principal, String name, String linkedin){
     ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
     Contact contact = new Contact(linkedin, name, user);
     contactRepository.save(contact);
     return new RedirectView("/addcontacts");

 }

}
