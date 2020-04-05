package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
import com.springframework.petclinic.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mike");
        owner1.setLastName("Wilson");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Marta");
        owner2.setLastName("Jonson");

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Alina");
        vet1.setLastName("Patrascu");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("George");
        vet2.setLastName("Cosbuc");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
