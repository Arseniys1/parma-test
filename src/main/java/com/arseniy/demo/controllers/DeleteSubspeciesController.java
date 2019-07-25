package com.arseniy.demo.controllers;

import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.models.Subspecies;
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
@RequestMapping(path="/deleteSubspecies")
public class DeleteSubspeciesController {

    @Autowired
    private SubspeciesRepository repository;


    @RequestMapping(method=RequestMethod.DELETE, produces="application/json")
    public String delete(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        Optional<Subspecies> findResult = this.repository.findById(id);

        if (!findResult.isPresent()) {
            return new ErrorResponse("Subspecies not found").toJSON();
        }

        Subspecies subspecies = findResult.get();

        this.repository.delete(subspecies);

        return new OKResponse().toJSON();
    }

}
