package com.optimize.land.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.optimize.common.entities.annotations.ConditionalNotNull;
import com.optimize.land.model.enumeration.RoleActor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ConditionalNotNull(booleanField = "hasConflict", dependentField = "conflict")
public class FindingDto {
    private Long id;
    @NotBlank(message = "Le NUP est obligatoire !")
    private String nup;
    private String region;
    private String prefecture;
    private String commune;
    private String canton;
    private String locality;
    private RoleActor personType;
    private String uin;
    private Boolean hasConflict;
    @Valid
    @NotNull(message = "Le checklist avant opération est obligatoire !")
    private CheckListOperationDto firstCheckListOperation;
    @Valid
    @NotNull(message = "Le checklist après opération est obligatoire !")
    private CheckListOperationDto lastCheckListOperation;
    private ConflictDto conflict;
    private String synchroBatchNumber;
    private String synchroPacketNumber;
}
