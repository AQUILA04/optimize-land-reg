package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.model.entity.FingerprintStore;
import com.optimize.land.repository.FingerprintStoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FingerprintStoreService extends GenericService<FingerprintStore, Long> {

    protected FingerprintStoreService(FingerprintStoreRepository repository) {
        super(repository);
    }
}
