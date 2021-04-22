package com.example.projBLSS.service;

import com.example.projBLSS.beans.Album;
import com.example.projBLSS.beans.Picture;
import com.example.projBLSS.beans.User;
import com.example.projBLSS.dto.AlbumDTO;
import com.example.projBLSS.dto.ResponseMessageDTO;
import com.example.projBLSS.exceptions.AlbumNotFoundException;
import com.example.projBLSS.exceptions.PictureNotFoundException;
import com.example.projBLSS.exceptions.UserNotFoundException;
import com.example.projBLSS.repository.AlbumRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Service

public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private ShutterstockUserDetailsService userService;

    @Autowired
    private Album album;


    @Autowired
    private Picture picture;

    @Autowired
    private PictureService pictureService;


    public ResponseEntity<ResponseMessageDTO> saveFromDTO(AlbumDTO albumDTO, HttpServletRequest request){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            User owner = this.userService.getUserFromRequest(request);
            Album album = dtoConverter.convertAlbumFromDTO(albumDTO, owner);
            this.save(album);
            message.setAnswer("Album was created");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            message.setAnswer("Album without owner. Please, try later");
            return new ResponseEntity<>(message, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        } catch (DataIntegrityViolationException e) {
            String answerText = "";
            if(e.getCause().getClass() == ConstraintViolationException.class){
                answerText = "Альбом с таким именем уже существует!";
            }else{
                answerText = "УПС! Произошла ошибка, пожалуйста, попробуйте позднее";
            }
            message.setAnswer(answerText);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    public AlbumDTO findByIdToResponse(Long id) throws AlbumNotFoundException{
        Album album = this.findById(id);
        if(album == null){
            throw new AlbumNotFoundException("Album with id " + id + "doesn't exist", HttpStatus.BAD_REQUEST);
        }
        AlbumDTO albumDTO = dtoConverter.convertAlbumToDTO(album);
        return albumDTO;
    }

    public void save(Album album){
        this.albumRepository.save(album);
    }

    public Album findById(Long id) throws AlbumNotFoundException {
        album = albumRepository.findByID(id);
        if (album == null){
            throw new AlbumNotFoundException("Альбом с таким id не существует", HttpStatus.BAD_REQUEST);
        }
        return album;
    }

    public void addPicture(byte[] file, String name, Long albumId) throws AlbumNotFoundException {
        try {
            album = this.findById(albumId);
            picture.setName(name);
            picture.setPict(file);
            pictureService.addPicture(picture);
            album.addPicture(picture);
            albumRepository.save(album);
        }catch (AlbumNotFoundException e){
            e.setErrMessage("Альбома по данному id не найдено");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }


    public void addExistingPicture(Long id, Long pictureId) throws AlbumNotFoundException, PictureNotFoundException {
        try{
            album = this.findById(id);
            picture = pictureService.getPicture(pictureId);
            album.addPicture(picture);
            albumRepository.save(album);
        }catch (AlbumNotFoundException e){
            e.setErrMessage("Альбома по данному id не найдено");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw e;
        }catch (PictureNotFoundException e){
            e.setErrMessage("Картинки с данным id не существует");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }




}
