package com.example.projBLSS.main_server.controllers;


import com.example.projBLSS.main_server.dto.PictureDTO;
import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.exceptions.UserNotFoundException;
import com.example.projBLSS.main_server.service.PictureService;
import com.example.projBLSS.main_server.service.ShutterstockUserDetailsService;
import com.example.projBLSS.rabbit_service.producing.ProducingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@Profile("dev")
public class PictureController {

    @Autowired
    private ShutterstockUserDetailsService userService;

    @Autowired
    private PictureService service;

    @Autowired
    private ProducingService producingService;

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

    }


    @PostMapping("/upload/{name}")
    public ResponseEntity uploadPicture(@PathVariable String name, @RequestBody byte[] file, HttpServletRequest request) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            picture.setID(null);
            picture.setPict(file);
            picture.setName(name);
            picture.setUserID(this.userService.getUserFromRequest(request).getID());
            service.addPicture(picture);
            message.setAnswer("Picture was added");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UserNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
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


    @PostMapping("/{id}/like/{count}")
    public ResponseEntity<ResponseMessageDTO> likePicture(@PathVariable Long id, @PathVariable Long count){
        ResponseMessageDTO message = new ResponseMessageDTO();
        producingService.likePicture(id, count);
        message.setAnswer("Запрос на увеличение лайка отправлен!");
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}