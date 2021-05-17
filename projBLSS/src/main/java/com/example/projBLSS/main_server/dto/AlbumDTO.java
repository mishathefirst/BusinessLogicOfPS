package com.example.projBLSS.main_server.dto;

import com.example.projBLSS.main_server.beans.Picture;

import java.io.Serializable;
import java.util.List;

public class AlbumDTO implements Serializable {

    private Long id;

    private String name;

    private Long user_id;

    private List<Picture> pictures;

    public AlbumDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
