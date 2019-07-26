package com.arseniy.demo.services;

import com.arseniy.demo.SubspeciesRepository;
import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Subspecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubspeciesService {

    @Autowired
    private SubspeciesRepository subspeciesRepository;

    public Iterable<Subspecies> getSubspecies() {
        return this.subspeciesRepository.findAll();
    }

    public Subspecies createSubspecies(String name) {
        Subspecies subspecies = new Subspecies(name);

        this.subspeciesRepository.save(subspecies);

        return subspecies;
    }

    public Subspecies editSubspecies(Long id, String name) throws ErrorResponseException {
        Optional<Subspecies> findResult = this.subspeciesRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Subspecies not found");
        }

        Subspecies subspecies = findResult.get();
        subspecies.setName(name);

        this.subspeciesRepository.save(subspecies);

        return subspecies;
    }

    public void deleteSubspecies(Long id) throws ErrorResponseException {
        Optional<Subspecies> findResult = this.subspeciesRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Subspecies not found");
        }

        Subspecies subspecies = findResult.get();

        this.subspeciesRepository.delete(subspecies);
    }

}
