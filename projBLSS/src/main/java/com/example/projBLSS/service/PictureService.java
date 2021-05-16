package com.example.projBLSS.service;

import com.example.projBLSS.beans.Picture;
import com.example.projBLSS.exceptions.PictureNotFoundException;
import com.example.projBLSS.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int likePicture(Long id, Long incValue){
        return pictureRepository.incrementLikeField(id, incValue);
    }
}