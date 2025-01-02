package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.jms.AfisProducer;
import com.optimize.land.jms.model.AfisMasterRequest;
import com.optimize.land.model.dto.ActorDto;
import com.optimize.land.model.entity.AbstractActor;
import com.optimize.land.model.entity.Registration;
import com.optimize.land.model.mapper.ActorMapper;
import com.optimize.land.repository.ActorRepository;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
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
        registration.addRid(UUID.randomUUID().toString());
        fingerprintStoreService.getRepository().saveAll(registration.getFingerprintStores());
        registration.updateFingerprint();
        create(registration);
        fingerprintStoreService.getRepository().saveAllAndFlush(registration.getFingerprintStores());
        afisProducer.sendMatchingRequest(new AfisMasterRequest(registration.getRid(),
                actorMapper.toSetFingerprintStoreDto(registration.getFingerprintStores())));
        return registration.getId();
    }
}
