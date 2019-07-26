package com.arseniy.demo.services;

import com.arseniy.demo.WardenRepository;
import com.arseniy.demo.exceptions.ErrorResponseException;
import com.arseniy.demo.models.Warden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WardenService {

    @Autowired
    private WardenRepository wardenRepository;

    public Iterable<Warden> getWardens() {
        return this.wardenRepository.findAll();
    }

    public Warden createWarden(String name, String family_name, String birth_day, Boolean married, Double salary) throws ErrorResponseException {
        Date birth_date = null;

        try {
            birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);
        } catch (ParseException e) {
            throw new ErrorResponseException("birth_day format dd.MM.yyyy");
        }

        Warden warden = new Warden(name, family_name, birth_date, married, salary);

        this.wardenRepository.save(warden);

        return warden;
    }

    public Warden editWarden(Long id, String name, String family_name, String birth_day, Boolean married, Double salary) throws ErrorResponseException {
        Optional<Warden> findResult = this.wardenRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Warden not found");
        }

        Date birth_date = null;

        try {
            birth_date = new SimpleDateFormat("dd.MM.yyyy").parse(birth_day);
        } catch (ParseException e) {
            throw new ErrorResponseException("birth_day format dd.MM.yyyy");
        }

        Warden warden = findResult.get();
        warden.setName(name);
        warden.setFamilyName(family_name);
        warden.setBirthDay(birth_date);
        warden.setMarried(married);
        warden.setSalary(salary);

        this.wardenRepository.save(warden);

        return warden;
    }

    public void deleteWarden(Long id) throws ErrorResponseException {
        Optional<Warden> findResult = this.wardenRepository.findById(id);

        if (!findResult.isPresent()) {
            throw new ErrorResponseException("Warden not found");
        }

        Warden warden = findResult.get();

        this.wardenRepository.delete(warden);
    }

}
