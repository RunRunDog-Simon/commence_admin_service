package com.gtelant.commerce_admin_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PosterNotFoundException extends RuntimeException {
    public PosterNotFoundException(long id){
        super("Poster not found with id:" + id);
    }
}
