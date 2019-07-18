package com.arseniy.demo.controllers;

import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.responses.OKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/getWardens")
public class GetWardensController {

    @Autowired
    private WardenRepository wardenRepository;

    @RequestMapping(method= RequestMethod.GET, produces="application/json")
    public String get() throws IOException {
        return new OKResponse(this.wardenRepository.findAll()).toJSON();
    }

}
