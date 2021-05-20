package com.example.projBLSS.main_server.service;

import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;



    public Picture addPicture(Picture picture) {
        return pictureRepository.save(picture);
    }


    public Picture getPicture(long id) throws PictureNotFoundException {
        Picture picture = pictureRepository.findByID(id);
        if (picture == null){
            throw new PictureNotFoundException("Picture with that id not found", HttpStatus.BAD_REQUEST);
        }
        return picture;
    }


    public Picture editPicture(Picture picture) {
        return pictureRepository.save(picture);
    }


    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }


    public void deletePicture(int id) {
        pictureRepository.deleteById((long) id);
    }


    public List<Picture> getAllPictures(int pageNumber, int pageSize) {
        return pictureRepository.findAll();
    }


    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }


    public long countPictures() {
        return pictureRepository.count();
    }



    @Profile("stats")
    public int incrementLikePicture(Long id, Long incValue) throws PictureNotFoundException {
        getPicture(id);
        return this.pictureRepository.incrementLikeField(id, incValue);
    }
}