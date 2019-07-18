package com.arseniy.demo.controllers;

import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.models.Subspecies;
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
@RequestMapping(path="/editSubspecies")
public class EditSubspeciesController {

    @Autowired
    private SubspeciesRepository repository;


    @RequestMapping(method=RequestMethod.PUT, produces="application/json")
    public String get(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {
        Optional<Subspecies> findResult = this.repository.findById(id);

        if (!findResult.isPresent()) {
            return new ErrorResponse("Subspecies not found").toJSON();
        }

        Subspecies subspecies = findResult.get();
        subspecies.setName(name);

        this.repository.save(subspecies);

        return new OKResponse(subspecies).toJSON();
    }

}
