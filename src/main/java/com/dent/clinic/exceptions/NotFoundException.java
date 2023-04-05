package com.dent.clinic.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
