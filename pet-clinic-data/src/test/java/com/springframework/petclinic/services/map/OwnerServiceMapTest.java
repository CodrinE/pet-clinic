package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long owner1Id = 1L;
    final Long owner2Id = 2L;
    final String lastName = "Jojo";

    @BeforeEach
    void setUp() {

        Owner owner = new Owner();
        owner.setId(owner1Id);
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(owner1Id);
        assertEquals(owner1Id, owner.getId());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(owner1Id);
        assertNull(ownerServiceMap.findById(owner1Id));
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(owner1Id));
        assertNull(ownerServiceMap.findById(owner1Id));
    }

    @Test
    void saveExistingId() {
        Owner owner2 = new Owner();
        owner2.setId(owner2Id);
        Owner saveOwner = ownerServiceMap.save(owner2);

        assertEquals(owner2Id, saveOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saveOwner =  ownerServiceMap.save(new Owner());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = new Owner();
        owner.setId(owner2Id);
        owner.setLastName(lastName);
        Owner saveOwner = ownerServiceMap.save(owner);

        assertNotNull(lastName);
        assertEquals(owner2Id, saveOwner.getId());
    }
}