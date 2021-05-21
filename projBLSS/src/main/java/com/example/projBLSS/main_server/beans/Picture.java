package com.example.projBLSS.main_server.beans;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "t_picture")
public class Picture {

    @Id
    @GeneratedValue
    private Long ID;
    private String name;
    @NotNull
    private Long userID;
    //private File pict;
    private byte[] pict;
    @Column(columnDefinition = "integer default 0")
    private Long likes = 0L;
    @ManyToMany(mappedBy = "pictures")
    private List<Album> albums;

    @Column(columnDefinition = "boolean default false")
    private boolean isNotificateUser;

    public Picture() {
    }

    public Picture(String name, byte[] pict) {
        this.name = name;
        this.pict = pict;
    }

    public Picture(Long ID, String name, byte[] pict) {
        this.ID = ID;
        this.name = name;
        this.pict = pict;
    }

    public byte[] getPict() {
        return pict;
    }




    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPict(byte[] pict) {
        this.pict = pict;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public boolean isNotificateUser() {
        return isNotificateUser;
    }

    public void setNotificateUser(boolean notificateUser) {
        isNotificateUser = notificateUser;
    }

    @Override
    public String toString() {
        return String.format("Picture{id=%d, name='%s'}", ID, name);
    }

}
