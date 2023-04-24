package com.education.model.util.exceptions;

import com.education.model.dto.AppealDto;

public class AppealNotValidException extends RuntimeException{
    public AppealNotValidException(String message) {
        super(message);
    }
}
