package com.optimize.land.repository;

import com.optimize.common.entities.exception.ResourceNotFoundException;
import com.optimize.land.model.entity.AbstractActor;

import java.util.Optional;

public interface ActorRepository extends BaseActorRepository<AbstractActor, Long> {

    Optional<AbstractActor> findByRid(String rid);

    default AbstractActor getByRid(String rid) {
        return findByRid(rid).orElseThrow(() -> new ResourceNotFoundException("Could not find actor by rid " + rid));
    }
}
