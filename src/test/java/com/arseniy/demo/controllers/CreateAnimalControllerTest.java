package com.arseniy.demo.controllers;

import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.models.Warden;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateAnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Autowired
    private WardenRepository wardenRepository;

    @Test
    public void post() throws Exception {
        Subspecies subspecies = new Subspecies("Какой-то вид");
        this.subspeciesRepository.save(subspecies);

        Warden warden = new Warden("Арсений", "Лебедев", new SimpleDateFormat("dd.MM.yyyy").parse("25.07.1999"), false, 100.98);
        this.wardenRepository.save(warden);

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/createAnimal")
                .param("name", "Вася")
                .param("birth_day", "25.07.1999")
                .param("subspecies_id", "1")
                .param("warden_ids", "1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"ok\":true,\"response\":{\"id\":1,\"subspecies\":{\"id\":1,\"name\":\"Какой-то вид\"},\"wardens\":[{\"id\":1,\"name\":\"Арсений\",\"married\":false,\"salary\":100.98,\"birthDay\":932839200000,\"familyName\":\"Лебедев\"}],\"name\":\"Вася\",\"birthDay\":932839200000}}"));
    }
}