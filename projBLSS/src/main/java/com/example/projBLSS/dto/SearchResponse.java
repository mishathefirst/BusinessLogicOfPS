package com.example.projBLSS.dto;

import java.util.ArrayList;

public class SearchResponse {

    private ArrayList<Long> indexes = new ArrayList<>();
    private ArrayList<String> picNames = new ArrayList<>();

    public SearchResponse(ArrayList<Long> indexes, ArrayList<String> picNames) {
        this.indexes = indexes;
        this.picNames = picNames;
    }

    public void setIndexes(ArrayList<Long> indexes) {
        this.indexes = indexes;
    }

    public void setPicNames(ArrayList<String> picNames) {
        this.picNames = picNames;
    }

    public ArrayList<Long> getIndexes() {
        return indexes;
    }

    public ArrayList<String> getPicNames() {
        return picNames;
    }
}
