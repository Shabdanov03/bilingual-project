package com.example.bilingualb8.exceptions;

/**
 * Shabdanov Ilim
 **/
public class BadCredentialException extends RuntimeException{
    public BadCredentialException() {

    }

    public BadCredentialException(String message) {
        super(message);
    }
}
