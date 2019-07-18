package com.arseniy.demo.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ErrorResponse implements Response {

    private boolean ok = false;

    private Object response = "Error";

    public ErrorResponse() {
    }

    public ErrorResponse(Object response) {
        this.response = response;
    }

    public boolean getOK() {
        return ok;
    }

    public Object getResponse() {
        return response;
    }

    public String toJSON() throws IOException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
