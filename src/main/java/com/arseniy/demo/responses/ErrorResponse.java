package com.arseniy.demo.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;

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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SORT_PROPERTIES_ALPHABETICALLY, true);
        return objectMapper.writeValueAsString(this);
    }
}
