package com.arseniy.demo.controllers;

import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.SubspeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/createSubspecies")
public class CreateSubspeciesController {

    @Autowired
    private SubspeciesService subspeciesService;

    @RequestMapping(method=RequestMethod.POST, produces="application/json")
    public String post(
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {
        return new OKResponse(this.subspeciesService.createSubspecies(name)).toJSON();
    }

}
