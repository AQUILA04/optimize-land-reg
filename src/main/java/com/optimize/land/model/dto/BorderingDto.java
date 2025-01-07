package com.optimize.land.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.optimize.land.model.enumeration.CardinalPoint;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BorderingDto {
    private Long id;
    private CardinalPoint cardinalPoint;
    private String uin;
    private CheckListOperationDto checkListOperation;
}
