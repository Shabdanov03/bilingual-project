package com.example.bilingualb8.exceptions;

/**
 * Shabdanov Ilim
 **/
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException() {

    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
