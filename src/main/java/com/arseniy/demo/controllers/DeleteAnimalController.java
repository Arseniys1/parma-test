package com.arseniy.demo.controllers;

import com.arseniy.demo.exceptions.ErrorResponseException;
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
@RequestMapping(path="/deleteAnimal")
public class DeleteAnimalController extends Controller {

    @Autowired
    private AnimalService animalService;

    @RequestMapping(method=RequestMethod.DELETE, produces="application/json")
    public String delete(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        try {
            this.animalService.deleteAnimal(id);

            return new OKResponse().toJSON();
        } catch (ErrorResponseException e) {
            throw new ErrorResponseException(e.getMessage());
        }
    }

}
