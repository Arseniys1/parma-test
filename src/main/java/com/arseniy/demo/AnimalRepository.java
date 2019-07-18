package com.arseniy.demo;

import com.arseniy.demo.models.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {



}
