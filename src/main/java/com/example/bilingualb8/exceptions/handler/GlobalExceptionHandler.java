package com.example.bilingualb8.excaption.handler;

import com.example.bilingualb8.excaption.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e ){
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),e.getMessage()
        );

    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse alreadyExistException(AlreadyExistException a){
        return new ExceptionResponse(
                HttpStatus.CONFLICT,
                a.getClass().getSimpleName(),
                a.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialExcaption.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse badCredential(BadCredentialExcaption b ){
        return new ExceptionResponse(HttpStatus.FORBIDDEN,
                b.getClass().getSimpleName(),
                b.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse badCredential(BadRequestException b ){
        return new ExceptionResponse(HttpStatus.FORBIDDEN,
                b.getClass().getSimpleName(),
                b.getMessage());
    }
}
