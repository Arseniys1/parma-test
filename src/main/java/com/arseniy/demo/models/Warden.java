package com.arseniy.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Warden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String family_name;

    @NotNull
    private Date birth_day;

    @NotNull
    private Boolean married;

    @NotNull
    @Column(columnDefinition = "Decimal(19,4)")
    private Double salary;

    @ManyToMany
    private Set<Animal> animals;

    public Warden() {
    }

    public Warden(@NotNull String name, @NotNull String family_name, @NotNull Date birth_day, @NotNull Boolean married, @NotNull Double salary) {
        this.name = name;
        this.family_name = family_name;
        this.birth_day = birth_day;
        this.married = married;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return family_name;
    }

    public Date getBirthDay() {
        return birth_day;
    }

    public Boolean getMarried() {
        return married;
    }

    public Double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String family_name) {
        this.family_name = family_name;
    }

    public void setBirthDay(Date birth_day) {
        this.birth_day = birth_day;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
