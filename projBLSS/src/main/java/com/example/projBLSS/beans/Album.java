package com.example.projBLSS.beans;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_album")
public class Album {
    @Id
    @GeneratedValue
    private Long ID;

    @NotNull
    private String name;

    @NotNull
    private Long user_id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Picture> pictures;

    public Album() {
    }

    public Album(String name, Long user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public void addPicture(Picture picture){
        this.pictures.add(picture);
    }
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
