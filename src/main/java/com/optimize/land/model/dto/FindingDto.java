package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.RoleActor;
import lombok.Data;

@Data
public class FindingDto {
    private Long id;
    private String nup;
    private String region;
    private String prefecture;
    private String commune;
    private String canton;
    private String locality;
    private RoleActor personType;
    private String uin;
    private Boolean hasConflict;
    private CheckListOperationDto firstCheckListOperation;
    private CheckListOperationDto lastCheckListOperation;
    private ConflictDto conflict;
}
