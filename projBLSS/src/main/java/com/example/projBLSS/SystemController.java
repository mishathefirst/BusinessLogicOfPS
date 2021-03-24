package com.example.projBLSS;


import com.example.projBLSS.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.File;


@RestController
public class SystemController {

    @Autowired
    private PictureService service;

    @GetMapping("/")
    public String showHello() {
        return "Hello!";
    }

    @GetMapping("/search/{keyword}")
    public String showSearchResults(@PathVariable String keyword) {
        return keyword;
    }


    @PostMapping("/upload")
    public String uploadPicture(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name,
                                @RequestBody byte[] picture) {
        final Picture picture1;
        picture1 = new Picture(id, name);

        service.addPicture(picture1);

        return "Picture added!";
    }


    @PostMapping("/hello")
    public String showHelloPost() {
        return "Hello POST!";
    }

}