package com.example.projBLSS;


import com.example.projBLSS.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;


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

    @GetMapping("/download/{keyword}")
    public PictureDownloadResponse downloadPicture(@PathVariable String keyword) {
        int index = 0;
        List<Picture> allPictures;
        allPictures = service.getAllPictures();
        for (int i = 0; i < allPictures.size(); i++) {
            Picture localPicture = allPictures.get(i);
            if (localPicture.getName().equals(keyword)) {
                index = i;
                break;
            }
        }
        byte[] pictureToSend = service.getPicture(index).getPict();

        return new PictureDownloadResponse(keyword, pictureToSend);
    }


    @PostMapping("/upload")
    public String uploadPicture(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name,
                                @RequestBody byte[] picture) {
        final Picture picture1;
        picture1 = new Picture((long) id, name, picture);

        service.addPicture(picture1);

        return "Picture added!";
    }


    @PostMapping("/hello")
    public String showHelloPost() {
        return "Hello POST!";
    }

}