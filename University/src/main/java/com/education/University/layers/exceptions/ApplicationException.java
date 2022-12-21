package com.education.University.layers.exceptions;
// All your custom exception MUST be extended from RuntimeException
public class ApplicationException extends RuntimeException{
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
