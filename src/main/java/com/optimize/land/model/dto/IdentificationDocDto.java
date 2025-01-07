package com.optimize.land.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.optimize.common.entities.annotations.Base64Image;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentificationDocDto {
    private Long id;
    @NotBlank(message = "Le type de document d'identification est obligatoire !")
    protected String identificationDocType;
    protected String otherIdentificationDocType;
    @NotBlank(message = "Le numéro du document d'identification est obligatoire !")
    protected String identificationDocNumber;
    @NotBlank(message = "La photo du document d'identification est obligatoire !")
    @Base64Image
    protected String identificationDocPhoto;
    protected String identificationDocPhotoContentType;
}
