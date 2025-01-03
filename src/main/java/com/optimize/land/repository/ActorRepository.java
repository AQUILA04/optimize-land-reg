package com.optimize.land.repository;

import com.optimize.common.entities.exception.ResourceNotFoundException;
import com.optimize.land.model.entity.AbstractActor;
import com.optimize.land.model.entity.Actor;
import com.optimize.land.model.enumeration.RegistrationStatus;

import java.util.Optional;

public interface ActorRepository extends BaseActorRepository<AbstractActor, Long> {

    Optional<AbstractActor> findByRid(String rid);

    Optional<Actor> findByUinAndRegistrationStatus(String rid, RegistrationStatus status);

    default AbstractActor getByRid(String rid) {
        return findByRid(rid).orElseThrow(() -> new ResourceNotFoundException("Could not find actor by rid " + rid));
    }

    default Actor getByUin(String uin) {
        return findByUinAndRegistrationStatus(uin, RegistrationStatus.ACTOR).orElse(null);
    }
}
