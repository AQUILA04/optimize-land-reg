package com.optimize.land.service;

import com.optimize.common.entities.service.GenericService;
import com.optimize.land.model.dto.FindingDto;
import com.optimize.land.model.entity.Finding;
import com.optimize.land.model.mapper.FindingMapper;
import com.optimize.land.repository.FindingRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FindingService extends GenericService<Finding, Long> {
    private final FindingMapper findingMapper;
    private final CheckListOperationService checkListOperationService;

    protected FindingService(FindingRepository repository,
                             FindingMapper findingMapper,
                             CheckListOperationService checkListOperationService) {
        super(repository);
        this.findingMapper = findingMapper;
        this.checkListOperationService = checkListOperationService;
    }

    public Long register(@NotNull FindingDto findingDto) {
        Finding finding = findingMapper.toEntity(findingDto);
        this.checkListOperationService.create(finding.getFirstCheckListOperation());
        this.checkListOperationService.create(finding.getLastCheckListOperation());
        create(finding);
        return finding.getId();
    }


}
