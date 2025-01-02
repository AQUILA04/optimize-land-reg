package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.MaritalStatus;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {
    protected Long id;
    @NotBlank(message = "actor.person.lastname.mandatory")
    protected String lastname;
    @NotBlank(message = "actor.person.firstname.mandatory")
    protected String firstname;
    @NotNull(message = "actor.person.sex.mandatory")
    protected Sex sex;
    @NotNull(message = "actor.person.marital-status.mandatory")
    protected MaritalStatus maritalStatus;
    @NotNull(message = "actor.person.birth-date.mandatory")
    protected LocalDate birthDate;
    @NotNull
    protected String placeOfBirth;
    @NotNull
    protected String nationality;
    protected String profession;
    protected String otherProfession;
    @NotNull
    protected String address;
    @NotNull
    protected String primaryPhone;
    protected String secondaryPhone;
    @NotNull
    protected String email;
    protected Boolean hasHandicap;
    protected String socioCulturalGroup;
    protected String handicapType;
    protected String otherHandicapType;
    protected String firstFingerprint;
    protected String secondFingerprint;
    protected String thirdFingerprint;
    protected String firstFingerName;
    protected String secondFingerName;
    protected String thirdFingerName;
    protected Boolean hasIDDoc;
    protected String identificationDocType;
    protected String otherIdentificationDocType;
    protected String identificationDocNumber;
    protected String identificationDocPhoto;
    protected String identificationDocPhotoContentType;
    protected String witnessUIN;
    @NotNull
    protected RegistrationStatus registrationStatus;
    protected String statusObservation;
    protected String rid;
    protected String synchroBatchNumber;
    protected String synchroPacketNumber;
}
