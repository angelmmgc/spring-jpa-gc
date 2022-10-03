package com.example.springjpagc.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServicioExceptions extends ResponseStatusException {

    public ServicioExceptions(String message) {

        super(HttpStatus.NOT_FOUND, message);
    }



}