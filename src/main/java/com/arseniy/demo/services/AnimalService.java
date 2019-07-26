package com.arseniy.demo.services;

import com.arseniy.demo.AnimalRepository;
import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Animal;
import com.arseniy.demo.models.Subspecies;
import com.arseniy.demo.models.Warden;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    @Autowired
    private WardenRepository wardenRepository;

    public Iterable<Animal> getAnimals() {
        return this.animalRepository.findAll();
    }

    public Animal createAnimal(String name, String birth_day, Long subspecies_id, Long[] warden_ids) throws ErrorResponseException {
        Optional<Subspecies> subspeciesFindResult = this.subspeciesRepository.findById(subspecies_id);

        if (!subspeciesFindResult.isPresent()) {
            throw new ErrorResponseException("Subspecies not found");
        }

        Iterable<Warden> wardensFindResult = this.wardenRepository.findAllById(Arrays.asList(warden_ids));

        if (IterableUtils.size(wardensFindResult) == 0) {
            throw new ErrorResponseException("Wardens not found");
        }

        Date birth_date = null;

        if (!birth_day.isEmpty()) {
            try {
                birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);
            } catch (ParseException e) {
                throw new ErrorResponseException("birth_day format dd.MM.yyyy");
            }
        }

        Animal animal = new Animal();
        animal.setName(name);
        if (birth_date != null) {
            animal.setBirthDay(birth_date);
        }
        animal.setSubspecies(subspeciesFindResult.get());
        animal.setWardens(new HashSet<Warden>((Collection) wardensFindResult));

        this.animalRepository.save(animal);

        return animal;
    }

    public Animal editAnimal(Long id, String name, String birth_day, Long subspecies_id, Long[] warden_ids) throws ErrorResponseException {
        Optional<Animal> findResult = this.animalRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Animal not found");
        }

        Animal animal = findResult.get();

        Optional<Subspecies> subspeciesFindResult = this.subspeciesRepository.findById(subspecies_id);

        if (!subspeciesFindResult.isPresent()) {
            throw new ErrorResponseException("Subspecies not found");
        }

        Iterable<Warden> wardensFindResult = this.wardenRepository.findAllById(Arrays.asList(warden_ids));

        if (IterableUtils.size(wardensFindResult) == 0) {
            throw new ErrorResponseException("Wardens not found");
        }

        Date birth_date = null;

        if (!birth_day.isEmpty()) {
            try {
                birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);
            } catch (ParseException e) {
                throw new ErrorResponseException("birth_day format dd.MM.yyyy");
            }
        }

        animal.setName(name);
        if (birth_date != null) {
            animal.setBirthDay(birth_date);
        }
        animal.setSubspecies(subspeciesFindResult.get());
        animal.setWardens(new HashSet<Warden>((Collection) wardensFindResult));

        this.animalRepository.save(animal);

        return animal;
    }

    public void deleteAnimal(Long id) throws ErrorResponseException {
        Optional<Animal> findResult = this.animalRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Animal not found");
        }

        Animal animal = findResult.get();

        this.animalRepository.delete(animal);
    }

}
