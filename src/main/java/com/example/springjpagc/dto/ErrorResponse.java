package com.example.springjpagc.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    private Integer status;
    private String message;
    private LocalDateTime date;

    public ErrorResponse(){}

    public ErrorResponse(Integer status, String message, LocalDateTime date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
