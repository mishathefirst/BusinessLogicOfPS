package com.example.projBLSS;


import com.example.projBLSS.beans.Picture;
import com.example.projBLSS.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SystemController {

    @Autowired
    private PictureService service;

    @GetMapping("/hello")
    public String showHello() {
        return "Hello!";
    }

    @GetMapping("/search/{keyword}")
    public SearchResponse showSearchResults(@PathVariable String keyword) {
        ArrayList<Long> index = new ArrayList<>();
        ArrayList<String> picNames = new ArrayList<>();
        List<Picture> allPictures = service.getAllPictures();
        for (long i = 0; i < allPictures.size(); i++) {
            Picture localPicture = allPictures.get((int) i);
            if (localPicture.getName().contains(keyword)) {
                index.add(localPicture.getId());
                picNames.add(localPicture.getName());
            }
        }
        return new SearchResponse(index, picNames);
    }

    @GetMapping("/download/{keyword}")
    public PictureDownloadResponse downloadPicture(@PathVariable String keyword) {
        long index = 0;
        List<Picture> allPictures;
        allPictures = service.getAllPictures();
        for (long i = 0; i < allPictures.size(); i++) {
            Picture localPicture = allPictures.get((int) i);
            if (localPicture.getName().equals(keyword)) {
                index = localPicture.getId();
                break;
            }
        }
        byte[] pictureToSend = service.getPicture(index).getPict();

        /*
        try {
            File fileToSend = File.createTempFile("image", ".jpg");
            FileOutputStream outputStream = new FileOutputStream(fileToSend);

            outputStream.write(pictureToSend);
            outputStream.flush();
            System.out.println(fileToSend);
            System.out.println(fileToSend.getAbsoluteFile());
            return new PictureDownloadResponse(keyword, fileToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PictureDownloadResponse("Nothing found");

         */

        return new PictureDownloadResponse(keyword, pictureToSend);


        //return index;
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