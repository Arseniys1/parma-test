package com.arseniy.demo.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
        return new ObjectMapper().writeValueAsString(this);
    }
}
