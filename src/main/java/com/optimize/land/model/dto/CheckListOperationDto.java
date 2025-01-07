package com.optimize.land.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
