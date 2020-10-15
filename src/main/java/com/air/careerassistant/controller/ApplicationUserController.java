package com.air.careerassistant.controller;

import com.air.careerassistant.model.job.Job;
import com.air.careerassistant.model.user.ApplicationUser;
import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class ApplicationUserController {

@Autowired
    private PasswordEncoder passwordEncoder;

@Autowired
    ApplicationUserRepository applicationUserRepository;

@GetMapping("/login")
    public String login (){
    return "login";
}

    @GetMapping("/signup")
    public String signup (){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String firstname, String lastname, String password, HttpServletRequest request){
    String hashedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser=new ApplicationUser(username, firstname, lastname, hashedPassword);
        applicationUserRepository.save(newUser);
        try {
            request.login(username,password);
        } catch (ServletException e) {
            System.out.println("login failed");
            e.printStackTrace();
        }
        return new RedirectView ("/");
    }

    @GetMapping("/allmyjobs")
    public String returnAllJobs(Model m, Principal principal){
        if (principal != null){
            m.addAttribute("principal",principal);
        }
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        m.addAttribute("user", user);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        m.addAttribute("currentDate", currentDate);
        String blackClass= "card-text p-3 mb-2 bg-dark text-white";
        m.addAttribute("blackClass",blackClass);

        // function here to compare createdAt and check if more than 30 days have passed
        return ("allmyjobs");
    }


}
