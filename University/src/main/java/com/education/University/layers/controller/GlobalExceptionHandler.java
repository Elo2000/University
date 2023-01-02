package com.education.University.layers.controller;

import com.education.University.layers.exceptions.ApplicationException;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.exceptions.SemanticException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundException(DataNotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SemanticException.class) // <-
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleSemanticException(SemanticException exception) {
        return new ErrorMessage(exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(ApplicationException.class) // <-
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGeneralApplicationException(ApplicationException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class) // <-
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(Exception exception) {
        //
        return new ErrorMessage(exception.getMessage());
    }

}
