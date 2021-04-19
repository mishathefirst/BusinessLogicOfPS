package com.example.projBLSS.service;

import com.example.projBLSS.beans.Picture;

import java.util.List;

public interface PictureService {

    Picture addPicture(Picture picture);
    Picture getPicture(long id);
    Picture editPicture(Picture picture);
    void deletePicture(Picture picture);
    void deletePicture(int id);
    List getAllPictures(int pageNumber, int pageSize);
    List getAllPictures();
    long countPictures();
}
