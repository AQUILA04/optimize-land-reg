package com.optimize.land.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InformalGroupDto {
    private Long id;
    private String uin;
    @NotNull
    private String groupName;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    private String secondaryPhoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String groupType;
    @NotNull
    private String representativeUIN;
    @NotNull
    private String representativeFullname;
    @NotNull
    private String secondaryRepresentativeUIN;
    @NotNull
    private String secondaryRepresentativeFullname;
    @NotNull
    private String thirdRepresentativeUIN;
    @NotNull
    private String thirdRepresentativeFullname;
    private String mandatePhoto;
    @NotNull
    private String mandatePhotoContentType;

}
