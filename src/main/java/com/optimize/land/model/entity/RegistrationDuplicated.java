package com.optimize.land.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegistrationDuplicated extends AbstractActor {
//    @PrePersist
//    public void setUp() {
//        this.id = null;
//        this.createdBy = null;
//        this.createdDate = null;
//    }
}
