package com.arseniy.demo.responses;

import java.io.IOException;

public interface Response {
    boolean getOK();

    Object getResponse();

    String toJSON() throws IOException;
}
