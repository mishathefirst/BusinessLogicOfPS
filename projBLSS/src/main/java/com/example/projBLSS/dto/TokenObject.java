package com.example.projBLSS.dto;

public class TokenObject extends ResponseMessageDTO{
    private String accessToken;
    private String refreshToken;
    public TokenObject(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenObject(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenObject() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
