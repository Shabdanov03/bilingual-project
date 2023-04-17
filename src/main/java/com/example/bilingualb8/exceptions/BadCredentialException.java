package com.example.bilingualb8.exceptions;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(){

    }
    public BadCredentialException(String massage){
        super(massage);
    }
}
