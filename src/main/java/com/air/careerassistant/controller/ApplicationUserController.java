package com.air.careerassistant.controller;

import com.air.careerassistant.model.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationUserController {

//@Autowired
//    private PasswordEncoder passwordEncoder;
@Autowired
    ApplicationUserRepository applicationUserRepository;
}
