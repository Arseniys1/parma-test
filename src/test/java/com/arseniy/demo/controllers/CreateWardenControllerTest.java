package com.arseniy.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateWardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void post() throws Exception {
        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/createWarden")
                .param("name", "Арсений")
                .param("family_name", "Лебедев")
                .param("birth_day", "25.07.1999")
                .param("married", "1")
                .param("salary", "100.98")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"ok\":true,\"response\":{\"id\":1,\"name\":\"Арсений\",\"married\":true,\"salary\":100.98,\"familyName\":\"Лебедев\",\"birthDay\":932839200000}}"));
    }
}