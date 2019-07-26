package com.arseniy.demo.controllers;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.models.Warden;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EditAnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Autowired
    private WardenRepository wardenRepository;

    @Test
    public void put() throws Exception {
        Subspecies subspecies = new Subspecies("Какой-то вид");
        this.subspeciesRepository.save(subspecies);

        Warden warden = new Warden("Арсений", "Лебедев", new SimpleDateFormat("dd.MM.yyyy").parse("25.07.1999"), false, 100.98);
        this.wardenRepository.save(warden);

        HashSet<Warden> wardens = new HashSet<Warden>();
        wardens.add(warden);

        Animal animal = new Animal(subspecies, "Вася", new SimpleDateFormat("dd.MM.yyyy").parse("25.07.1999"), wardens);
        this.animalRepository.save(animal);

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/editAnimal")
                .param("id", "1")
                .param("name", "Петя")
                .param("birth_day", "09.08.1998")
                .param("subspecies_id", "1")
                .param("warden_ids", "1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"ok\":true,\"response\":{\"birthDay\":902599200000,\"id\":1,\"name\":\"Петя\",\"subspecies\":{\"id\":1,\"name\":\"Какой-то вид\"},\"wardens\":[{\"birthDay\":932839200000,\"familyName\":\"Лебедев\",\"id\":1,\"married\":false,\"name\":\"Арсений\",\"salary\":100.98}]}}"));
    }
}