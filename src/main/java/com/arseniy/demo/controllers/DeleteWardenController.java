package com.arseniy.demo.controllers;

import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Warden;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping(path="/deleteWarden")
public class DeleteWardenController {

    @Autowired
    private WardenRepository repository;


    @RequestMapping(method=RequestMethod.DELETE, produces="application/json")
    public String get(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        Optional<Warden> findResult = this.repository.findById(id);

        if (!findResult.isPresent()) {
            return new ErrorResponse("Warden not found").toJSON();
        }

        Warden warden = findResult.get();

        this.repository.delete(warden);

        return new OKResponse().toJSON();
    }

}
