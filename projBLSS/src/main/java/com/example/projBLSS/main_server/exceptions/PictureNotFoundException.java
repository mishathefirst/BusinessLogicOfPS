package com.example.projBLSS.main_server.exceptions;

import org.springframework.http.HttpStatus;

public class PictureNotFoundException extends Throwable {
    private String errMessage;
    private HttpStatus errStatus;

    public PictureNotFoundException(String errMessage, HttpStatus errStatus) {
        super();
        this.errMessage = errMessage;
        this.errStatus = errStatus;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public HttpStatus getErrStatus() {
        return errStatus;
    }

    public void setErrStatus(HttpStatus errStatus) {
        this.errStatus = errStatus;
    }
}
