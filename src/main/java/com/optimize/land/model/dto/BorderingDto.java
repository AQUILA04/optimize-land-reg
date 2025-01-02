package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.CardinalPoint;
import lombok.Data;

@Data
public class BorderingDto {
    private Long id;
    private CardinalPoint cardinalPoint;
    private String uin;
    private CheckListOperationDto checkListOperation;
}
