package com.arseniy.demo.controllers;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.models.Warden;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path="/deleteAnimal")
public class DeleteAnimalController {

    @Autowired
    private AnimalRepository repository;


    @RequestMapping(method=RequestMethod.DELETE, produces="application/json")
    public String get(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        Optional<Animal> findResult = this.repository.findById(id);

        if (!findResult.isPresent()) {
            return new ErrorResponse("Animal not found").toJSON();
        }

        Animal animal = findResult.get();

        this.repository.delete(animal);

        return new OKResponse().toJSON();
    }

}
