package com.example.projBLSS;

public class PictureDownloadResponse {

    //private Long id;
    private String name;
    private byte[] pict;

    public PictureDownloadResponse(String name, byte[] pict) {
        //this.id = id;
        this.name = name;
        this.pict = pict;
    }

    public String getName() {
        return name;
    }

    public byte[] getPict() {
        return pict;
    }
/*
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

 */

    public void setName(String name) {
        this.name = name;
    }

    public void setPict(byte[] pict) {
        this.pict = pict;
    }
}