package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
import com.springframework.petclinic.services.SpecialityService;
import com.springframework.petclinic.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Override
    public void run(String... args) throws Exception {

        boolean exists = petTypeService.findAll().isEmpty();

        if (exists){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mike");
        owner1.setLastName("Wilson");
        owner1.setAddress("124 New Road");
        owner1.setCity("Miami");
        owner1.setTelephone("98643782673");

        ownerService.save(owner1);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Job");
        owner1.getPets().add(mikesPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Marta");
        owner2.setLastName("Jonson");
        owner1.setAddress("124 New Road");
        owner1.setCity("Miami");
        owner1.setTelephone("98643782673");

        ownerService.save(owner2);

        Pet martasPet = new Pet();
        martasPet.setPetType(saveCatPetType);
        martasPet.setOwner(owner2);
        martasPet.setBirthDate(LocalDate.now());
        martasPet.setName("Fluffy");
        owner2.getPets().add(martasPet);

        System.out.println("Loaded owners...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Alina");
        vet1.setLastName("Patrascu");
        vet1.getSpecialities().add(saveSurgery);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("George");
        vet2.setLastName("Cosbuc");
        vet2.getSpecialities().add(saveDentistry);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
