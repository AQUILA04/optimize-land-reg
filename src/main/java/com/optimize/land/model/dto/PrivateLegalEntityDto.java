package com.optimize.land.model.dto;

import com.optimize.common.entities.annotations.Base64Image;
import com.optimize.common.entities.annotations.ValidPhoneNumber;
import com.optimize.land.model.enumeration.PrivateEntityType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrivateLegalEntityDto {
    private Long id;
    private String uin;
    @NotNull
    private String companyName;
    @NotNull
    private String address;
    @NotNull
    @ValidPhoneNumber
    private String phoneNumber;
    @ValidPhoneNumber
    private String secondaryPhoneNumber;
    private String email;
    private PrivateEntityType entityType;
    private String identificationDocType;
    private String identificationDocNumber;
    @Base64Image
    private String identificationDocPhoto;
    @NotNull
    private String identificationDocPhotoContentType;
    @NotNull
    private String mainActivity;
    @NotNull
    private String acronym;
    @NotNull
    private LocalDate companyCreatedDate;
    @NotNull
    private String representativeUIN;
    @NotNull
    private String representativeFullname;
    private String rid;
}
