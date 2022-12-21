package com.education.University.layers.exceptions;

public class DataNotFoundException extends ApplicationException{
    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
