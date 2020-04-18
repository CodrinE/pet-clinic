package com.springframework.petclinic.services.springDataJpa;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.repositories.OwnerRepository;
import com.springframework.petclinic.repositories.PetRepository;
import com.springframework.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    public static final String LAST_NAME_1 = "George";
    public static final String LAST_NAME_2 = "Maria";
    public static final Long ID_1 = 1L;
    public static final Long ID_2 = 2L;
    private Owner testOwner1;
    private Owner testOwner2;

    @InjectMocks
    OwnerSDJPAService service;

    @BeforeEach
    void setUp() {
        testOwner1 = Owner.builder().id(ID_1).lastName(LAST_NAME_1).build();
        testOwner2 = Owner.builder().id(ID_2).lastName(LAST_NAME_2).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(testOwner1);

        Owner smith = service.findByLastName(LAST_NAME_1);

        assertEquals(LAST_NAME_1, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = new HashSet<>();;
        returnOwners.add(testOwner2);
        returnOwners.add(testOwner1);

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(testOwner1));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(testOwner2);

        Owner savedOwner = service.save(testOwner1);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(testOwner1);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID_1);

        verify(ownerRepository).deleteById(anyLong());
    }
}