package com.optimize.land.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckListOperationDto {
    private Long id;
    @NotBlank(message = "Le NIU du maire est obligatoire !")
    private String mayorUIN;
    @NotBlank(message = "Le NIU du chef traditionnel est obligatoire")
    private String traditionalChiefUIN;
    @NotBlank(message = "Le NIU du notable est obligatoire !")
    private String notableUIN;
    @NotBlank(message = "Le NIU du géomètre est obligatoire !")
    private String geometerUIN;
    @NotBlank(message = "Le NIU du propriétaire ou du mandataire est obligatoire !")
    private String ownerUIN;
    @NotNull(message = "Les limitrophes sont obligatoires !")
    private Set<BorderingDto> borderingList;
    private String interestedThirdPartyUIN;
    @NotBlank(message = "Le NIU du Topographe est obligatoire !")
    private String topographerUIN;
    @NotBlank(message = "Le NIU de l'agent socio-foncier est obligatoire !")
    private String socialLandAgentUIN;
    private String tiersUIN;
    private String tiersRole;
}
