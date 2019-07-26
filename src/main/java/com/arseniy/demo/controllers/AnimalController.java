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
public class AnimalController extends Controller {

    @Autowired
    private AnimalService animalService;

    @RequestMapping(path="/getAnimals", method= RequestMethod.GET, produces="application/json")
    public String getAnimals() throws IOException {
        return new OKResponse(this.animalService.getAnimals()).toJSON();
    }

    @RequestMapping(path="/createAnimal", method=RequestMethod.POST, produces="application/json")
    public String createAnimal(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "birth_day") String birth_day,
            @RequestParam(value = "subspecies_id", required = true) Long subspecies_id,
            @RequestParam(value = "warden_ids", required = true) Long[] warden_ids
    ) throws IOException {
        Animal animal = this.animalService.createAnimal(name, birth_day, subspecies_id, warden_ids);

        return new OKResponse(animal).toJSON();
    }

    @RequestMapping(path="/editAnimal", method=RequestMethod.PUT, produces="application/json")
    public String editAnimal(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "birth_day") String birth_day,
            @RequestParam(value = "subspecies_id", required = true) Long subspecies_id,
            @RequestParam(value = "warden_ids", required = true) Long[] warden_ids
    ) throws IOException {
        Animal animal = this.animalService.editAnimal(id, name, birth_day, subspecies_id, warden_ids);

        return new OKResponse(animal).toJSON();
    }

    @RequestMapping(path="deleteAnimal", method=RequestMethod.DELETE, produces="application/json")
    public String deleteAnimal(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        this.animalService.deleteAnimal(id);

        return new OKResponse().toJSON();
    }

}
