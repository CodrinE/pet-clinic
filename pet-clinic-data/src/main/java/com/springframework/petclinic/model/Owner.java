package com.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owners")
public class Owner extends Person{

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String telephone;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String  address, String  city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(!isNull(pets)) {
            this.pets = pets;
        }
    }

    public  Pet getPet(String name){
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String petName = pet.getName();
                petName = petName.toLowerCase();
                if (petName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
