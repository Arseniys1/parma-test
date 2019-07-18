package com.arseniy.demo.controllers;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.responses.OKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/getSubspecies")
public class GetSubspeciesController {

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @RequestMapping(method= RequestMethod.GET, produces="application/json")
    public String get() throws IOException {
        return new OKResponse(this.subspeciesRepository.findAll()).toJSON();
    }

}
