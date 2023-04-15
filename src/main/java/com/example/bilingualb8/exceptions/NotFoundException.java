package com.example.bilingualb8.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){

    }
    public NotFoundException(String massage){
        super(massage);
    }
}
