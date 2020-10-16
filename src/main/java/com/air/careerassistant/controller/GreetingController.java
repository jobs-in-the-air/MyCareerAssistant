package com.air.careerassistant.controller;

import com.air.careerassistant.websocket.Greeting;
import com.air.careerassistant.websocket.HelloMessage;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @GetMapping("/sharejobs")
    public String renderIndex() { return "sharejobs"; }
}
