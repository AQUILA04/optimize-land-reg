package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.PublicEntityType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PublicLegalEntityDto {
    private Long id;
    private String uin;
    @NotNull
    private PublicEntityType publicEntityType;
}
