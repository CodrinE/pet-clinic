package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.services.VisitService;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
@Profile({"default", "map"})
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
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if(isNull(visit.getPet())
                || isNull(visit.getPet().getOwner())
                || isNull(visit.getPet().getId())
                || isNull(visit.getPet().getOwner().getId())){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
