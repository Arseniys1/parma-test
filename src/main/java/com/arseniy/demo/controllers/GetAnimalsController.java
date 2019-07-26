package com.arseniy.demo.controllers;

import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/getAnimals")
public class GetAnimalsController extends Controller {

    @Autowired
    private AnimalService animalService;

    @RequestMapping(method= RequestMethod.GET, produces="application/json")
    public String get() throws IOException {
        return new OKResponse(this.animalService.getAnimals()).toJSON();
    }

}
