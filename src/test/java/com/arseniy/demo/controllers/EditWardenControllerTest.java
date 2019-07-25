package com.arseniy.demo.controllers;

import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Warden;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EditWardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WardenRepository wardenRepository;

    @Test
    public void put() throws Exception {
        Warden warden = new Warden("Арсений", "Лебедев", new SimpleDateFormat("dd.MM.yyyy").parse("25.07.1999"), false, 100.98);
        this.wardenRepository.save(warden);

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/editWarden")
                .param("id", "1")
                .param("name", "Вася")
                .param("family_name", "Пупкин")
                .param("birth_day", "30.10.1979")
                .param("married", "0")
                .param("salary", "10.50")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"ok\":true,\"response\":{\"id\":1,\"name\":\"Вася\",\"married\":false,\"salary\":10.5,\"familyName\":\"Пупкин\",\"birthDay\":310071600000}}"));
    }
}