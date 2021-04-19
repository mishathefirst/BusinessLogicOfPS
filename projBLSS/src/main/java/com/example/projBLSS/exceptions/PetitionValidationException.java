package com.example.projBLSS.exceptions;

import org.springframework.http.HttpStatus;

public class PetitionValidationException extends Throwable{
    private String errMessage;
    private HttpStatus errStatus;

    public PetitionValidationException(String errMessage, HttpStatus errStatus) {
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
