package com.air.careerassistant.controller;

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
    password=passwordEncoder.encode(password);
        ApplicationUser newUser=new ApplicationUser(username, firstname, lastname, password);
        applicationUserRepository.save(newUser);
        try {
            request.login(username,password);
        } catch (ServletException e) {
            System.out.println("login failed");
            e.printStackTrace();
        }
        return new RedirectView ("/");
    }

//    @GetMapping("/allmyjobs")
//    public String returnAllJobs(Model m, Principal principal){
//        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
//        m.ad
//    }

}
