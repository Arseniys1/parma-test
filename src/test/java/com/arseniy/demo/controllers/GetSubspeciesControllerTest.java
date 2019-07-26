package com.arseniy.demo.controllers;

import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.models.Subspecies;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetSubspeciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Test
    public void get() throws Exception {
        Subspecies subspecies = new Subspecies("Какой-то вид");
        this.subspeciesRepository.save(subspecies);

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/getSubspecies")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"ok\":true,\"response\":[{\"id\":1,\"name\":\"Какой-то вид\"}]}"));
    }
}