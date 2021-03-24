package com.example.projBLSS.service;

import com.example.projBLSS.Picture;
import com.example.projBLSS.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture addPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture getPicture(int id) {
        return pictureRepository.findById((long)id).get();
    }

    @Override
    public Picture editPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }

    @Override
    public void deletePicture(int id) {
        pictureRepository.deleteById((long) id);
    }

    @Override
    public List<Picture> getAllPictures(int pageNumber, int pageSize) {
        return pictureRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public long countPictures() {
        return pictureRepository.count();
    }
}