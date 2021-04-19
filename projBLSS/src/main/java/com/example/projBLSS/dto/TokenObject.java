package com.example.projBLSS.dto;

public class TokenObject {
    private String accessToken;

    public TokenObject(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
