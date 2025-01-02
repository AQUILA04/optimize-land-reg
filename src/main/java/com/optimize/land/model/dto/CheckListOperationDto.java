package com.optimize.land.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CheckListOperationDto {
    private Long id;
    private String mayorUIN;
    private String traditionalChiefUIN;
    private String notableUIN;
    private String geometerUIN;
    private String ownerUIN;
    private Set<BorderingDto> borderingList;
    private String interestedThirdPartyUIN;
}
