package com.air.careerassistant.controller;

import com.air.careerassistant.websocket.Greeting;
import com.air.careerassistant.websocket.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Greeting(principal.getName() + ": " + message.getName());
    }

    @GetMapping("/sharejobs")
    public String renderIndex(Model m, Principal principal) {
        m.addAttribute("principal", principal);
        return "sharejobs";
    }
}
