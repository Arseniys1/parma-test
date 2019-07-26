package com.arseniy.demo.controllers;

import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.responses.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

public class Controller {

    @ExceptionHandler(ErrorResponseException.class)
    public String handleException(ErrorResponseException e) {
        try {
            return new ErrorResponse(e.getMessage()).toJSON();
        } catch (IOException ee) {
            return ee.getMessage();
        }
    }

}
