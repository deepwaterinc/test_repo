package com.education.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> notValidAppealException(HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
            var msg = e.getMessage();
            if (msg.startsWith("400 : \"Appeal not valid")) {
                msg = msg.replace(';', '\r')
                        .replace("\"", "");
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        }
        throw e;
    }
}