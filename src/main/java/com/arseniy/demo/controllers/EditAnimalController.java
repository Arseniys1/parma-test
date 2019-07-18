package com.arseniy.demo.controllers;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.models.Warden;
import com.arseniy.demo.responses.ErrorResponse;
import com.arseniy.demo.responses.OKResponse;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path="/editAnimal")
public class EditAnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Autowired
    private WardenRepository wardenRepository;


    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public String get(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "birth_day") String birth_day,
            @RequestParam(value = "subspecies_id", required = true) Long subspecies_id,
            @RequestParam(value = "warden_ids", required = true) Long[] warden_ids
    ) throws ParseException, IOException {

        Optional<Animal> findResult = this.animalRepository.findById(id);

        if (!findResult.isPresent()) return new ErrorResponse("Animal not found").toJSON();

        Animal animal = findResult.get();

        Optional<Subspecies> subspeciesFindResult = this.subspeciesRepository.findById(subspecies_id);

        if (!subspeciesFindResult.isPresent()) return new ErrorResponse("Subspecies not found").toJSON();

        Iterable<Warden> wardensFindResult = this.wardenRepository.findAllById(Arrays.asList(warden_ids));

        if (IterableUtils.size(wardensFindResult) == 0) return new ErrorResponse("Wardens not found").toJSON();

        Date birth_date = null;

        if (!birth_day.equals("")) birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);

        animal.setName(name);
        if (birth_date != null) animal.setBirthDay(birth_date);
        animal.setSubspecies(subspeciesFindResult.get());
        animal.setWardens(new HashSet<Warden>((Collection) wardensFindResult));

        this.animalRepository.save(animal);

        return new OKResponse(animal).toJSON();
    }

}
