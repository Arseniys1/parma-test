package com.arseniy.demo.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;

public class OKResponse implements Response {

    private boolean ok = true;

    private Object response = "OK";

    public OKResponse() {
    }

    public OKResponse(Object response) {
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
