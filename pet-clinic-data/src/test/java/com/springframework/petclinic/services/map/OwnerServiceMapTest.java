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
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(owner1Id).lastName(lastName).build());
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
        Owner saveOwner = ownerServiceMap.save(Owner.builder().id(owner2Id).lastName(lastName).build());

        assertEquals(owner2Id, saveOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saveOwner =  ownerServiceMap.save(Owner.builder().build());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner saveOwner = ownerServiceMap.save(Owner.builder().id(owner2Id).lastName(lastName).build());

        assertNotNull(lastName);
        assertEquals(owner2Id, saveOwner.getId());
    }
}