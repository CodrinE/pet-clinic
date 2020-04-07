package com.springframework.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "specilities")
public class Speciality extends BaseEntity {

    @Column
    private String description;
}
