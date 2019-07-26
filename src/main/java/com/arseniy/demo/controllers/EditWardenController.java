package com.arseniy.demo.controllers;

import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Warden;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/editWarden")
public class EditWardenController {

    @Autowired
    private WardenService wardenService;

    @RequestMapping(method=RequestMethod.PUT, produces="application/json")
    public String put(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "family_name", required = true) String family_name,
            @RequestParam(value = "birth_day", required = true) String birth_day,
            @RequestParam(value = "married", required = true) Boolean married,
            @RequestParam(value = "salary", required = true) Double salary
    ) throws IOException {
        try {
            Warden warden = this.wardenService.editWarden(id, name, family_name, birth_day, married, salary);

            return new OKResponse(warden).toJSON();
        } catch (ErrorResponseException e) {
            return new ErrorResponse(e.getMessage()).toJSON();
        }
    }

}
