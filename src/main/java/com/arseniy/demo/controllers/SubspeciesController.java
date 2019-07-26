package com.arseniy.demo.controllers;

import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.SubspeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SubspeciesController extends Controller {

    @Autowired
    private SubspeciesService subspeciesService;

    @RequestMapping(path="/getSubspecies", method= RequestMethod.GET, produces="application/json")
    public String getSubspecies() throws IOException {
        return new OKResponse(this.subspeciesService.getSubspecies()).toJSON();
    }

    @RequestMapping(path="/createSubspecies", method=RequestMethod.POST, produces="application/json")
    public String createSubspecies(
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {
        return new OKResponse(this.subspeciesService.createSubspecies(name)).toJSON();
    }

    @RequestMapping(path="/editSubspecies", method=RequestMethod.PUT, produces="application/json")
    public String editSubspecies(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name
    ) throws IOException {
        Subspecies subspecies = this.subspeciesService.editSubspecies(id, name);

        return new OKResponse(subspecies).toJSON();
    }

    @RequestMapping(path="/deleteSubspecies", method=RequestMethod.DELETE, produces="application/json")
    public String deleteSubspecies(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        this.subspeciesService.deleteSubspecies(id);

        return new OKResponse().toJSON();
    }

}
