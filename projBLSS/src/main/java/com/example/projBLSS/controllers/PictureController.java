package com.example.projBLSS.controllers;


import com.example.projBLSS.dto.PictureDTO;
import com.example.projBLSS.beans.Picture;
import com.example.projBLSS.dto.ResponseMessageDTO;
import com.example.projBLSS.exceptions.PictureNotFoundException;
import com.example.projBLSS.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PictureController {

    @Autowired
    private PictureService service;

    @Autowired
    private Picture picture;


    @GetMapping("/search/{keyword}")
    public ResponseEntity<PictureDTO> showSearchResults(@PathVariable String keyword) {
        PictureDTO pictureDTO = new PictureDTO();

        ArrayList<Long> index = new ArrayList<>();
        ArrayList<String> picNames = new ArrayList<>();
        List<Picture> allPictures = service.getAllPictures();

        for (long i = 0; i < allPictures.size(); i++) {
            Picture localPicture = allPictures.get((int) i);
            if (localPicture.getName().contains(keyword)) {
                index.add(localPicture.getID());
                picNames.add(localPicture.getName());
            }
        }
        pictureDTO.setIndexes(index);
        pictureDTO.setPicNames(picNames);
        pictureDTO.setAnswer("Found images:");
        return new ResponseEntity<>(pictureDTO, HttpStatus.OK);
    }

    @GetMapping("/download/{keyword}")
    public ResponseEntity<PictureDTO> downloadPicture(@PathVariable String keyword) {

        PictureDTO pictureDTO = new PictureDTO();

        long index = 0;
        List<Picture> allPictures;
        allPictures = service.getAllPictures();
        for (long i = 0; i < allPictures.size(); i++) {
            Picture localPicture = allPictures.get((int) i);
            if (localPicture.getName().equals(keyword)) {
                index = localPicture.getID();
                break;
            }
        }
        try {
            byte[] pictureToSend = service.getPicture(index).getPict();
            pictureDTO.setPict(pictureToSend);
            pictureDTO.setName(keyword);
            pictureDTO.setAnswer("Picture was downloaded successful");
            return new ResponseEntity<>(pictureDTO, HttpStatus.OK);
        }catch (PictureNotFoundException e){
            pictureDTO.setAnswer("Picture with this id doesn't exist");
            return new ResponseEntity<>(pictureDTO, HttpStatus.BAD_REQUEST);
        }

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




        //return index;
    }


    @PostMapping("/upload/{name}")
    public ResponseEntity uploadPicture(@PathVariable String name, @RequestBody byte[] file) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        picture.setPict(file);
        picture.setName(name);
        service.addPicture(picture);
        message.setAnswer("Picture was added");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }




    @PostMapping("/{id}/change/name")
    public ResponseEntity<ResponseMessageDTO> changePictureName(@PathVariable Long id, @RequestBody PictureDTO pictureDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            picture = service.getPicture(id);
        }catch (PictureNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        picture.setName(pictureDTO.getName());
        service.editPicture(picture);
        message.setAnswer("Name was changed");
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }


    @PostMapping("/{id}/change/delete/name")
    public ResponseEntity<ResponseMessageDTO> deletePictureName(@PathVariable Long id){
        return null;
    }
}