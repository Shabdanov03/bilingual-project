package com.example.bilingualb8.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionResponse extends RuntimeException{
    private HttpStatus httpStatus;
    private String exceptionClassName;
    private String message;


    public ExceptionResponse(HttpStatus conflict, String simpleName, String message) {
        this.httpStatus=getHttpStatus();
        this.exceptionClassName=getExceptionClassName();
        this.message=getMessage();
    }
}
