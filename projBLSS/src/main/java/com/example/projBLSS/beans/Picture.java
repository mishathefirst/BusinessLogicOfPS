package com.example.projBLSS.beans;

import javax.persistence.*;
import java.util.List;


@Entity

public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    //private File pict;
    private byte[] pict;

    @ManyToMany(mappedBy = "pictures")
    private List<Album> albums;

    public Picture() {
    }

    public Picture(String name, byte[] pict) {
        this.name = name;
        this.pict = pict;
    }

    public Picture(Long ID, String name, byte[] pict) {
                   //File pict) {
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

    @Override
    public String toString() {
        return String.format("Picture{id=%d, name='%s'}", ID, name);
    }

}
