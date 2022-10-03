package com.example.springjpagc.controller;



import com.example.springjpagc.dto.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class BaseController {


    //ERROR
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundExceptions(ChangeSetPersister.NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage(), LocalDateTime.now());
        return error;
    }

    //ERROR EN LA ENTRADA DEL DATO
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNumberFormatExceptions(NumberFormatException ex) {
        String message ="el id no es correcto, tiene que ser un numero: %s";
        ErrorResponse error = new ErrorResponse(400, message, LocalDateTime.now());
        return error;
    }

    // EXCEPCION POR NO ENCONTRAR EL ELEMENTO BUSCADO
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException ex) {

        ErrorResponse error = new ErrorResponse(404, ex.getMessage(), LocalDateTime.now());
        return error;
    }




}
