package com.arseniy.demo.controllers;

import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.SubspeciesService;
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
    private SubspeciesService subspeciesService;

    @RequestMapping(method=RequestMethod.PUT, produces="application/json")
    public String put(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {
        try {
            Subspecies subspecies = this.subspeciesService.editSubspecies(id, name);

            return new OKResponse(subspecies).toJSON();
        } catch (ErrorResponseException e) {
            return new ErrorResponse(e.getMessage()).toJSON();
        }
    }

}
