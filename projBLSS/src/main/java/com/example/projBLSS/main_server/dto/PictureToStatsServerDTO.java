package com.example.projBLSS.main_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PictureToStatsServerDTO implements Serializable {
    private Long id;
    private Long countLikesToAdd;

    public PictureToStatsServerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountLikesToAdd() {
        return countLikesToAdd;
    }

    public void setCountLikesToAdd(Long countLikesToAdd) {
        this.countLikesToAdd = countLikesToAdd;
    }
}

