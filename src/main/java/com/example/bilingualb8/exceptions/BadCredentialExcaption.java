package com.example.bilingualb8.exceptions;

public class BadCredentialExcaption extends RuntimeException{
    public BadCredentialExcaption(){

    }
    public BadCredentialExcaption(String massage){
        super(massage);
    }
}
