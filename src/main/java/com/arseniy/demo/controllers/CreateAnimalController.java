package com.arseniy.demo.controllers;

import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/createAnimal")
public class CreateAnimalController extends Controller {

    @Autowired
    private AnimalService animalService;

    @RequestMapping(method=RequestMethod.POST, produces="application/json")
    public String post(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "birth_day") String birth_day,
            @RequestParam(value = "subspecies_id", required = true) Long subspecies_id,
            @RequestParam(value = "warden_ids", required = true) Long[] warden_ids
    ) throws IOException {
        Animal animal = this.animalService.createAnimal(name, birth_day, subspecies_id, warden_ids);

        return new OKResponse(animal).toJSON();
    }

}
