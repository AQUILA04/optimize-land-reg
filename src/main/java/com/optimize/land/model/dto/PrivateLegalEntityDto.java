package com.optimize.land.model.dto;

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
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private String email;
    private PrivateEntityType entityType;
    private String identificationDocType;
    private String identificationDocNumber;
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
