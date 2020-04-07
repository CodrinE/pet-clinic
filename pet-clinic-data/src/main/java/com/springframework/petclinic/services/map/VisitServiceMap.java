package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.services.VisitService;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class VisitServiceMap extends  AbstractMapService<Visit, Long>  implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if(isNull(object.getPet())
                || isNull(object.getPet().getOwner())
                || isNull(object.getPet().getId())
                || isNull(object.getPet().getOwner().getId())){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
