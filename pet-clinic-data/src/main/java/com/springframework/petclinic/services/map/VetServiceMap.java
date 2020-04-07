package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Speciality;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.SpecialityService;
import com.springframework.petclinic.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

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
    public void delete(Vet vet){
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {

        if(!vet.getSpecialities().isEmpty()){
            vet.getSpecialities().forEach(speciality -> {
                if(isNull(speciality.getId())){
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }
        return super.save(vet);
    }

}
