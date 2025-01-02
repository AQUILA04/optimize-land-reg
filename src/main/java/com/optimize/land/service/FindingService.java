package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.model.entity.Finding;
import com.optimize.land.repository.FindingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FindingService extends GenericService<Finding, Long> {

    protected FindingService(FindingRepository repository) {
        super(repository);
    }
}
