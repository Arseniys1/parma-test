package com.arseniy.demo.controllers;

import com.arseniy.demo.models.Warden;
import com.arseniy.demo.responses.OKResponse;
import com.arseniy.demo.services.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WardenController extends Controller {

    @Autowired
    private WardenService wardenService;

    @RequestMapping(path="/getWardens", method= RequestMethod.GET, produces="application/json")
    public String getWardens() throws IOException {
        return new OKResponse(this.wardenService.getWardens()).toJSON();
    }

    @RequestMapping(path="/createWarden", method=RequestMethod.POST, produces="application/json")
    public String createWarden(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "family_name", required = true) String family_name,
            @RequestParam(value = "birth_day", required = true) String birth_day,
            @RequestParam(value = "married", required = true) Boolean married,
            @RequestParam(value = "salary", required = true) Double salary
    ) throws IOException {
        Warden warden = this.wardenService.createWarden(name, family_name, birth_day, married, salary);

        return new OKResponse(warden).toJSON();
    }

    @RequestMapping(path="/editWarden", method=RequestMethod.PUT, produces="application/json")
    public String editWarden(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "family_name", required = true) String family_name,
            @RequestParam(value = "birth_day", required = true) String birth_day,
            @RequestParam(value = "married", required = true) Boolean married,
            @RequestParam(value = "salary", required = true) Double salary
    ) throws IOException {
        Warden warden = this.wardenService.editWarden(id, name, family_name, birth_day, married, salary);

        return new OKResponse(warden).toJSON();
    }

    @RequestMapping(path="/deleteWarden", method=RequestMethod.DELETE, produces="application/json")
    public String deleteWarden(
            @RequestParam(value = "id", required = true) Long id
    ) throws IOException {
        this.wardenService.deleteWarden(id);

        return new OKResponse().toJSON();
    }

}
