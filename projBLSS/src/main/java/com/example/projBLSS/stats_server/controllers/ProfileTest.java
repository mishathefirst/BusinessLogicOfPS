package com.example.projBLSS.stats_server.controllers;

import com.example.projBLSS.rabbit_service.producing.RabbitLikeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileTest {

//    @Autowired
//    private RabbitLikeProducer sender;
//
//    @GetMapping("/test")
//    public String testProfile(){
//        sender.produce();
//        return "Hello";
//
//    }
}
