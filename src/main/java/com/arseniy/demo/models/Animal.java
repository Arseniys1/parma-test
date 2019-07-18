package com.arseniy.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subspecies_id")
    private Subspecies subspecies;

    @NotNull
    @ManyToMany
    private Set<Warden> wardens;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    private Date birth_day;

    public Animal() {
    }

    public Animal(@NotNull Subspecies subspecies, @NotNull String name, Date birth_day, @NotNull Set<Warden> wardens) {
        this.subspecies = subspecies;
        this.name = name;
        this.birth_day = birth_day;
        this.wardens = wardens;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birth_day;
    }

    public void setBirthDay(Date birth_day) {
        this.birth_day = birth_day;
    }

    public Subspecies getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(Subspecies subspecies) {
        this.subspecies = subspecies;
    }

    public Set<Warden> getWardens() {
        return wardens;
    }

    public void setWardens(Set<Warden> wardens) {
        this.wardens = wardens;
    }
}
