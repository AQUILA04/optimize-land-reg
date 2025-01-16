package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.model.entity.FingerprintMatchingHistory;
import com.optimize.land.repository.FingerprintMatchingHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class FingerprintMatchingHistoryService extends GenericService<FingerprintMatchingHistory, Long> {

    protected FingerprintMatchingHistoryService(FingerprintMatchingHistoryRepository repository) {
        super(repository);
    }
}
