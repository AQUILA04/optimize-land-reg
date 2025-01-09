package com.optimize.land.repository;

import com.optimize.common.entities.exception.ResourceNotFoundException;
import com.optimize.land.model.entity.AbstractActor;
import com.optimize.land.model.entity.Actor;
import com.optimize.land.model.entity.Registration;
import com.optimize.land.model.enumeration.RegistrationStatus;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends BaseActorRepository<AbstractActor, Long> {

    Optional<AbstractActor> findByRid(String rid);

    Optional<Registration> findByRidAndRegistrationStatusIn(String rid, List<RegistrationStatus> status);

    Optional<Actor> findByUinAndRegistrationStatus(String rid, RegistrationStatus status);

    default AbstractActor getByRid(String rid) {
        return findByRid(rid).orElseThrow(() -> new ResourceNotFoundException("Could not find actor by rid " + rid));
    }

    default Registration getRegistrationByRid(String rid) {
        return findByRidAndRegistrationStatusIn(rid, List.of(RegistrationStatus.PENDING, RegistrationStatus.QUEUED)).orElseThrow(() -> new ResourceNotFoundException("Could not find registration by rid " + rid));
    }

    default Actor getByUin(String uin) {
        return findByUinAndRegistrationStatus(uin, RegistrationStatus.ACTOR).orElse(null);
    }
}
