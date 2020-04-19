package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean exists = petTypeService.findAll().isEmpty();

        if (exists){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = PetType.builder().name("Dog").build();
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = Owner.builder()
                .firstName("Mike")
                .lastName("Wilson")
                .address("124 New Road")
                .city("Miami")
                .telephone("98643782673")
                .build();

        ownerService.save(owner1);

        Pet mikesPet = Pet.builder()
                .petType(saveDogPetType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Job").build();

        petService.save(mikesPet);

        owner1.getPets().add(mikesPet);

        Owner owner2 = Owner.builder()
                .firstName("Marta")
                .lastName("Jonson")
                .address("124 New Road")
                .city("Miami")
                .telephone("98643782673")
                .build();

        ownerService.save(owner2);

        Pet martasPet = Pet.builder()
                .petType(saveCatPetType)
                .owner(owner2)
                .birthDate(LocalDate.now())
                .name("Fluffy")
                .build();

        petService.save(martasPet);

        owner2.getPets().add(martasPet);


        Visit catVisit = new Visit();
        catVisit.setPet(martasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

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
