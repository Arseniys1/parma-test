package com.arseniy.demo.controllers;

import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.SubspeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/getSubspecies")
public class GetSubspeciesController {

    @Autowired
    private SubspeciesService subspeciesService;

    @RequestMapping(method= RequestMethod.GET, produces="application/json")
    public String get() throws IOException {
        return new OKResponse(this.subspeciesService.getSubspecies()).toJSON();
    }

}
