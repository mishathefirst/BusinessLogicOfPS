package com.example.projBLSS.beans;

import javax.persistence.*;
import java.io.File;


@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //private File pict;
    private byte[] pict;

    public Picture() {
    }

    public Picture(Long id, String name, byte[] pict) {
                   //File pict) {
        this.id = id;
        this.name = name;
        this.pict = pict;
    }

    public byte[] getPict() {
        return pict;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPict(byte[] pict) {
        this.pict = pict;
    }

    @Override
    public String toString() {
        return String.format("Picture{id=%d, name='%s'}", id, name);
    }

}
