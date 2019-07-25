package com.arseniy.demo.controllers;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.responses.OKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path="/createSubspecies")
public class CreateSubspeciesController {

    @Autowired
    private SubspeciesRepository repository;


    @RequestMapping(method=RequestMethod.POST, produces="application/json")
    public String post(
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {

        Subspecies subspecies = new Subspecies(name);

        this.repository.save(subspecies);

        return new OKResponse(subspecies).toJSON();
    }

}
