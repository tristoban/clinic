package com.dent.clinic.exceptions;

import org.hibernate.annotations.NotFound;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
