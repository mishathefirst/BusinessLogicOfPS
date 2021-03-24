package com.example.projBLSS;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;


@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //private File pict;

    public Picture() {
    }

    public Picture(int id, String name) {
                   //File pict) {
        this.id = id;
        this.name = name;
        //this.pict = pict;
    }
/*
    public File getPict() {
        return pict;
    }

 */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPict(File pict) {
        //this.pict = pict;
    }

    @Override
    public String toString() {
        return String.format("Picture{id=%d, name='%s'}", id, name);
    }

}
