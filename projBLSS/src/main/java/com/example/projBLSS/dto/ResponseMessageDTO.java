package com.example.projBLSS.dto;

import java.io.Serializable;

public class ResponseMessageDTO implements Serializable {
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
