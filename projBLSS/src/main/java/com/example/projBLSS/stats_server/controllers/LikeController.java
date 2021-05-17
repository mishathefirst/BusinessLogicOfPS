package com.example.projBLSS.stats_server.controllers;


import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {


    @Autowired
    private PictureService pictureService;




//    @PostMapping("/{id}")
//    public ResponseEntity<ResponseMessageDTO> likePicture(@PathVariable Long id){
//        ResponseMessageDTO message = new ResponseMessageDTO();
//
//
//    }
}
