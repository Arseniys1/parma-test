package com.arseniy.demo.controllers;

import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Warden;
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
@RequestMapping(path="/createWarden")
public class CreateWardenController {

    @Autowired
    private WardenRepository repository;


    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public String get(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "family_name", required = true) String family_name,
            @RequestParam(value = "birth_day", required = true) String birth_day,
            @RequestParam(value = "married", required = true) Boolean married,
            @RequestParam(value = "salary", required = true) Double salary
    ) throws ParseException, IOException {
        Date birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);

        Warden warden = new Warden(name, family_name, birth_date, married, salary);

        this.repository.save(warden);

        return new OKResponse(warden).toJSON();
    }

}
