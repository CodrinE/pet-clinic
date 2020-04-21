package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {

    private final Long petTypeId = 1L;
    PetTypeServiceMap petTypeServiceMap;

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(petTypeId);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteByIdNonExistingId() {
        petTypeServiceMap.deleteById(5L);
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteByIdNullId() {
        petTypeServiceMap.deleteById(null);
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(petTypeId));
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        petTypeServiceMap.delete( PetType.builder().id(5L).build());
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        petTypeServiceMap.delete(PetType.builder().build());
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteNull() {
        petTypeServiceMap.delete(null);
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void save(){
        Long petTypeId = 2L;
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
        assertEquals(petTypeId, savedPetType.getId());
    }

    @Test
    void saveNoId() {
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().build());
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2, petTypeServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
        assertEquals(petTypeId, savedPetType.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().id(id).build());

        assertEquals(id, savedPetType.getId());
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void findByIdExistingId() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void findByIdNotExistingId() {
        PetType petType = petTypeServiceMap.findById(5L);
        assertNull(petType);
    }

    @Test
    void findByIdNullId() {
        PetType petType = petTypeServiceMap.findById(null);
        assertNull(petType);
    }
}