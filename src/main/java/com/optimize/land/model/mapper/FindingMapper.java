package com.optimize.land.model.mapper;

import com.optimize.common.entities.mapper.BaseMapper;
import com.optimize.land.model.dto.BorderingDto;
import com.optimize.land.model.dto.CheckListOperationDto;
import com.optimize.land.model.dto.ConflictDto;
import com.optimize.land.model.dto.FindingDto;
import com.optimize.land.model.entity.Bordering;
import com.optimize.land.model.entity.CheckListOperation;
import com.optimize.land.model.entity.Conflict;
import com.optimize.land.model.entity.Finding;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FindingMapper extends BaseMapper<Finding, FindingDto> {

    CheckListOperation toCheckListOperation(CheckListOperationDto checkListOperationDto);
    CheckListOperationDto toCheckListOperationDto(CheckListOperation checkListOperation);

    Bordering toBordering(BorderingDto borderingDto);
    BorderingDto toBorderingDto(Bordering bordering);
    Set<Bordering> toBorderingSet(Set<BorderingDto> borderingSet);
    Set<BorderingDto> toBorderingDtoSet(Set<Bordering> borderingSet);

    Conflict toConflict(ConflictDto conflictDto);
    ConflictDto toConflictDto(Conflict conflict);




}
