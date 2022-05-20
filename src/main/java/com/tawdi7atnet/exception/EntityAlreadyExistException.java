package com.tawdi7atnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException() {

    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }


}
