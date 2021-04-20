package com.example.projBLSS.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PictureDTO implements Serializable {

    private ArrayList<Long> indexes = new ArrayList<>();
    private ArrayList<String> picNames = new ArrayList<>();

    private String name;
    private byte[] pict;

    private String answer;


    public PictureDTO() {
    }

    public PictureDTO(String name, byte[] pict) {
        this.name = name;
        this.pict = pict;
    }

    public ArrayList<Long> getIndexes() {
        return indexes;
    }

    public void setIndexes(ArrayList<Long> indexes) {
        this.indexes = indexes;
    }

    public ArrayList<String> getPicNames() {
        return picNames;
    }

    public void setPicNames(ArrayList<String> picNames) {
        this.picNames = picNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPict() {
        return pict;
    }

    public void setPict(byte[] pict) {
        this.pict = pict;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
