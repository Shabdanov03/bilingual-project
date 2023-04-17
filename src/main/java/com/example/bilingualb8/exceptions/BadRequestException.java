package com.example.bilingualb8.exceptions;

/**
 * Shabdanov Ilim
 **/
public class BadRequestException  extends RuntimeException{
    public BadRequestException() {

    }

    public BadRequestException(String message) {
        super(message);
    }
}
