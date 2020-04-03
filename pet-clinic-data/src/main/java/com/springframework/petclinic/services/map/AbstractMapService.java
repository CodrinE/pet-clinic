package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.BaseEntity;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    protected Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    protected T findById(ID id) {
        return map.get(id);
    }

    protected T save(T object) {
        if (nonNull(object)) {
            if (isNull(object.getId())) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }
        else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    protected void deleteById(ID id) {
        map.remove(id);
    }

    protected void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        Long nextId;
        try{
            nextId= Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException ex){
            nextId = 1L;
        }
        return nextId;
    }
}
