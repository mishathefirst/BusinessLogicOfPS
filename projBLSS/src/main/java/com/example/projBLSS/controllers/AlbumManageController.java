package com.example.projBLSS.controllers;

import com.example.projBLSS.XA.AlbumDeleteXA;
import com.example.projBLSS.beans.Album;
import com.example.projBLSS.dto.AlbumDTO;
import com.example.projBLSS.dto.ResponseMessageDTO;
import com.example.projBLSS.exceptions.AlbumNotFoundException;
import com.example.projBLSS.exceptions.AlbumValidationException;
import com.example.projBLSS.exceptions.PictureNotFoundException;
import com.example.projBLSS.service.AlbumService;
import com.example.projBLSS.validation.ValidationAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/album")
public class AlbumManageController {

    @Autowired
    private ValidationAlbumService validationAlbumService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumDeleteXA albumDeleteXA;

    @Autowired
    private Album album;


    @PutMapping("/create")
    public ResponseEntity<ResponseMessageDTO> createAlbum(@RequestBody AlbumDTO albumDTO, HttpServletRequest request){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            validationAlbumService.validateAlbumDTO(albumDTO);
        }catch (AlbumValidationException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return albumService.saveFromDTO(albumDTO, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbum(@PathVariable Long id){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            AlbumDTO albumDTO = this.albumService.findByIdToResponse(id);
            return new ResponseEntity<>(albumDTO, HttpStatus.OK);
        }catch (AlbumNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Long id) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            album = albumService.findById(id);
        }catch (AlbumNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }
        try {
            albumDeleteXA.deleteAlbum(album);
        }catch (PictureNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }
        message.setAnswer("Альбом был удален");
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PutMapping("/{id}/add/{name}")
    public ResponseEntity addPictureToAlbum(@PathVariable Long id, @RequestBody byte[] picture, @PathVariable String name){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            album = albumService.findById(id);
            albumService.addPicture(picture, name, id);
        }catch (AlbumNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }
        message.setAnswer("Картинка была успешно добавлена!");
        return new ResponseEntity(message, HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}/add/exist/{p_id}")
    public ResponseEntity addPictureExistingToAlbum(@PathVariable Long id, @PathVariable Long p_id) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            album = albumService.findById(id);
            System.out.println(p_id);
            albumService.addExistingPicture(id, p_id);
        }catch (AlbumNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }catch (PictureNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }
        message.setAnswer("Картинка была успешно добавлена!");
        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }



}
