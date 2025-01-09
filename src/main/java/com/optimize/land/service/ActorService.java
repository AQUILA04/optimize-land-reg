package com.optimize.land.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.optimize.common.entities.enums.State;
import com.optimize.common.entities.exception.ApplicationException;
import com.optimize.common.entities.service.GenericService;
import com.optimize.common.securities.security.services.UserService;
import com.optimize.land.client.AfisClient;
import com.optimize.land.jms.AfisProducer;
import com.optimize.land.jms.model.AfisMasterRequest;
import com.optimize.land.jms.model.RegistrationProcessorFeedback;
import com.optimize.land.model.dto.ActorDto;
import com.optimize.land.model.dto.BioAuthDto;
import com.optimize.land.model.dto.FingerprintAuthenticationResp;
import com.optimize.land.model.entity.*;
import com.optimize.land.model.enumeration.ActorType;
import com.optimize.land.model.enumeration.BioAuthResponse;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.SynchroType;
import com.optimize.land.model.mapper.ActorMapper;
import com.optimize.land.repository.ActorRepository;
import com.optimize.land.util.UniqueIDGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Transactional(readOnly = true)
@Slf4j(topic = "EventLog")
public class ActorService extends GenericService<AbstractActor, Long> {
    private final ActorMapper actorMapper;
    private final FingerprintStoreService fingerprintStoreService;
    private final SynchroHistoryService synchroHistoryService;
    private final AfisProducer afisProducer;
    private final AfisClient afisClient;
    private UserService userService;

    protected ActorService(ActorRepository repository,
                           ActorMapper actorMapper,
                           FingerprintStoreService fingerprintStoreService,
                           SynchroHistoryService synchroHistoryService,
                           AfisProducer afisProducer,
                           AfisClient afisClient) {
        super(repository);
        this.actorMapper = actorMapper;
        this.fingerprintStoreService = fingerprintStoreService;
        this.synchroHistoryService = synchroHistoryService;
        this.afisProducer = afisProducer;
        this.afisClient = afisClient;
    }

    @Transactional
    public String register(@NotNull ActorDto actorDto) throws JsonProcessingException {
        synchroHistoryService.receivedPacket(actorDto.getSynchroBatchNumber(),
                actorDto.getSynchroPacketNumber(), SynchroType.ACTOR);
        actorDto.validateUniqueActorType();
        Registration registration = actorMapper.toRegistration(actorDto);
        registration.validateUniqueActorType();
        final String rid = UniqueIDGenerator.generateRID();
        registration.addRid(rid);
        registration.setOperatorAgent(userService.getCurrentUser().getUsername());
        fingerprintStoreService.getRepository().saveAll(registration.getFingerprintStores());
        //registration.updateFingerprint();
        create(registration);
        //fingerprintStoreService.getRepository().saveAllAndFlush(registration.getFingerprintStores());
        if(ActorType.PHYSICAL_PERSON.equals(registration.getType())) {
            afisProducer.sendMatchingRequest(new AfisMasterRequest(registration.getRid(),
                    registration.getFingerprintStores()));
        } else {
            validateLegalEntity(registration);
        }
        return "{\"rid\":"+rid +"}";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void validate(String rid) {
        Registration registration = (Registration) getRepository().getByRid(rid);
        Actor actor = new Actor();
                actor = actorMapper.registrationToActor(registration);
        actor.addUin(UniqueIDGenerator.generateUIN());
        getRepository().delete(registration);
        create(actor);
        synchroHistoryService.successPacket(actor.getSynchroBatchNumber(), actor.getSynchroPacketNumber());
    }

    @Transactional(noRollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void failed(String rid, String message) {
        Registration registration = getRepository().getRegistrationByRid(rid);
        RegistrationFailed failed = actorMapper.registrationToRegistrationFailed(registration);
        failed.setRegistrationStatus(RegistrationStatus.FAILED);
        failed.setStatusObservation(message);
        getRepository().delete(registration);
        create(failed);
        synchroHistoryService.failedPacket(failed.getSynchroBatchNumber(), failed.getSynchroPacketNumber());
    }

    @Transactional
    public void duplicate(String rid, String message) {
        Registration registration = getRepository().getRegistrationByRid(rid);
        RegistrationDuplicated duplicated = actorMapper.registrationToRegistrationDuplicated(registration);
        duplicated.setRegistrationStatus(RegistrationStatus.FAILED);
        duplicated.setStatusObservation(message);
        getRepository().delete(registration);
        create(duplicated);
        synchroHistoryService.duplicatedPacket(duplicated.getSynchroBatchNumber(), duplicated.getSynchroPacketNumber());
    }

    @Transactional
    public void afterMatchingOperation(RegistrationProcessorFeedback feedback) {
        try {
            if (Boolean.TRUE.equals(feedback.getFoundMatch())) {
                log.info("===> AFIS DUPLICATED UPDATE");
                duplicate(feedback.getRid(), feedback.getMatchedRID());
            } else {
                log.info("===> AFIS VALIDATED UPDATE");
                validate(feedback.getRid());
            }
        } catch (Exception e) {
            log.error("===> AFIS UPDATE FAILED");
            log.error(e.getLocalizedMessage());
            failed(feedback.getRid(), e.getLocalizedMessage());
        }
    }

    public Page<AbstractActor> getByStatus(RegistrationStatus status, Pageable pageable) {
        Page<AbstractActor> pageActor = getRepository().findByRegistrationStatusAndStateOrderByIdDesc(status, State.ENABLED, pageable);
        pageActor.getContent().forEach(AbstractActor::getAllOperations);
        return pageActor;
    }

    public FingerprintAuthenticationResp bioAuth(BioAuthDto dto) {
        Actor actor = getRepository().getByUin(dto.getUin());
        FingerprintAuthenticationResp resp = new FingerprintAuthenticationResp();
        if (Objects.isNull(actor)) {
            resp.setStatus(BioAuthResponse.UIN_NOT_FOUND);
            return resp;
        }
//        if (!actor.getRole().equals(dto.getRole())) {
//            resp.setStatus(BioAuthResponse.ROLE_NOT_MATCH);
//            return resp;
//        }
        dto.setRid(actor.getRid());
        String fingerprint = dto.getFingerprint().split(",")[1];
        dto.setFingerprint(fingerprint);
        try {
            if (BioAuthResponse.MATCH.equals(afisClient.bioAuthRequest(dto))) {
                resp.setStatus(BioAuthResponse.MATCH);
                resp.setActor(actor.toActorModel());
            } else {
                resp.setStatus(BioAuthResponse.FINGERPRINT_NOT_MATCH);
            }
        } catch (Exception e) {
            log.error("ERROR: {}",e.getLocalizedMessage());
            throw new ApplicationException(e.getLocalizedMessage());
        }

        return resp;
    }

    @Transactional
    public void validateLegalEntity(Registration registration) {
        //TODO: gérer ça avec un event
        afisClient.sendLegalEntityFingerprint(registration.getFingerprintStores());
        validate(registration.getRid());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbstractActor> getAll() {
        return super.getAll();
    }

    @Override
    public Page<AbstractActor> getAll(Pageable pageable) {
        Page<AbstractActor> pageActor = getRepository().findByRegistrationStatusAndStateOrderByIdDesc(RegistrationStatus.ACTOR, State.ENABLED, pageable);
        pageActor.getContent().forEach(AbstractActor::getAllOperations);
        return pageActor;
    }

    public ActorRepository getRepository() {
        return (ActorRepository) repository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
