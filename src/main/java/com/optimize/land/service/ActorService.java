package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.jms.AfisProducer;
import com.optimize.land.jms.model.AfisMasterRequest;
import com.optimize.land.jms.model.RegistrationProcessorFeedback;
import com.optimize.land.model.dto.ActorDto;
import com.optimize.land.model.entity.*;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.mapper.ActorMapper;
import com.optimize.land.repository.ActorRepository;
import com.optimize.land.util.UniqueIDGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j(topic = "EventLog")
public class ActorService extends GenericService<AbstractActor, Long> {
    private final ActorMapper actorMapper;
    private final FingerprintStoreService fingerprintStoreService;
    private final SynchroHistoryService synchroHistoryService;
    private final AfisProducer afisProducer;

    protected ActorService(ActorRepository repository,
                           ActorMapper actorMapper,
                           FingerprintStoreService fingerprintStoreService,
                           SynchroHistoryService synchroHistoryService,
                           AfisProducer afisProducer) {
        super(repository);
        this.actorMapper = actorMapper;
        this.fingerprintStoreService = fingerprintStoreService;
        this.synchroHistoryService = synchroHistoryService;
        this.afisProducer = afisProducer;
    }

    @Transactional
    public Long register(@NotNull ActorDto actorDto) {
        synchroHistoryService.receivedPacket(actorDto.getSynchroBatchNumber(),
                actorDto.getSynchroPacketNumber());
        actorDto.validateUniqueActorType();
        Registration registration = actorMapper.toRegistration(actorDto);
        registration.addRid(UniqueIDGenerator.generateRID());
        fingerprintStoreService.getRepository().saveAll(registration.getFingerprintStores());
        registration.updateFingerprint();
        create(registration);
        fingerprintStoreService.getRepository().saveAllAndFlush(registration.getFingerprintStores());
        afisProducer.sendMatchingRequest(new AfisMasterRequest(registration.getRid(),
                actorMapper.toSetFingerprintStoreDto(registration.getFingerprintStores())));
        return registration.getId();
    }

    private void validate(String rid) {
        Registration registration = (Registration) getRepository().getByRid(rid);
        Actor actor = actorMapper.registrationToActor(registration);
        actor.addUin(UniqueIDGenerator.generateUIN());
        create(actor);
        getRepository().delete(registration);
        synchroHistoryService.successPacket(actor.getSynchroBatchNumber(), actor.getSynchroPacketNumber());
    }

    private void failed(String rid, String message) {
        Registration registration = (Registration) getRepository().getByRid(rid);
        RegistrationFailed failed = actorMapper.registrationToRegistrationFailed(registration);
        failed.setRegistrationStatus(RegistrationStatus.FAILED);
        failed.setStatusObservation(message);
        create(failed);
        getRepository().delete(registration);
        synchroHistoryService.failedPacket(failed.getSynchroBatchNumber(), failed.getSynchroPacketNumber());
    }

    private void duplicate(String rid, String message) {
        Registration registration = (Registration) getRepository().getByRid(rid);
        RegistrationDuplicated duplicated = actorMapper.registrationToRegistrationDuplicated(registration);
        duplicated.setRegistrationStatus(RegistrationStatus.FAILED);
        duplicated.setStatusObservation(message);
        create(duplicated);
        getRepository().delete(registration);
        synchroHistoryService.duplicatedPacket(duplicated.getSynchroBatchNumber(), duplicated.getSynchroPacketNumber());
    }

    public void afterMatchingOperation(RegistrationProcessorFeedback feedback) {
        try {
            if (Boolean.TRUE.equals(feedback.getIsFoundMatch())) {
                duplicate(feedback.getRid(), feedback.getMatchedRID());
            } else {
                validate(feedback.getRid());
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            failed(feedback.getRid(), e.getLocalizedMessage());
        }

    }

    public ActorRepository getRepository() {
        return (ActorRepository) repository;
    }
}
