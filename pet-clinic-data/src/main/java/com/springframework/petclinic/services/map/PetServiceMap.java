package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

    @Override
    public Set<Vet> findAll(){
        return super.findAll();
    }

    @Override
    public Vet findById(Long id){
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id){
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object){
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

}
