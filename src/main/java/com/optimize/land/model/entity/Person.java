package com.optimize.land.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimize.common.entities.annotations.ConditionalNotNull;
import com.optimize.common.entities.annotations.ValidPhoneNumber;
import com.optimize.common.entities.entity.Auditable;
import com.optimize.land.model.enumeration.MaritalStatus;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"lastname", "firstname", "sex", "marital_status", "birth_date",
                "place_of_birth", "nationality", "profession", "address", "primary_phone", "email"},
        name = "person_unique_constraint")})
@ConditionalNotNull(booleanField = "hasIDDoc", dependentField = "identificationDoc")
//@ConditionalNotNull(booleanField = "hasHandicap", dependentField = "handicapType")
public class Person extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @NotNull
    @Size(min = 2, max = 25)
    @Column(name = "lastname", length = 25)
    protected String lastname;

    @NotNull
    @Size(min = 2, max = 55)
    @Column(name = "firstname", length = 55, nullable = false)
    protected String firstname;

    //@NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    protected Sex sex;

    @Column(name = "uin", unique = true)
    protected String uin;

    //@NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    protected MaritalStatus maritalStatus;

    //@NotNull
    @Column(name = "birth_date")
    @PastOrPresent
    protected LocalDate birthDate;

    //@NotNull
    @Size(min = 2, max = 60)
    @Column(name = "place_of_birth", length = 60)
    protected String placeOfBirth;

    //@NotNull
    @Size(min = 2, max = 60)
    @Column(name = "nationality", length = 60)
    protected String nationality;

    @Column(name = "profession")
    protected String profession;

    @Column(name = "other_profession")
    protected String otherProfession;

    //@NotNull
    @Size(min = 2, max = 70)
    @Column(name = "address", length = 70)
    protected String address;

    @NotNull
    @Size(min = 8, max = 11)
    @Column(name = "primary_phone", length = 11, nullable = false, unique = true)
    @ValidPhoneNumber
    protected String primaryPhone;

    @Size(min = 8, max = 11)
    @Column(name = "secondary_phone", length = 11, unique = true)
    @ValidPhoneNumber
    protected String secondaryPhone;

    //@NotNull
    @Column(name = "email", unique = true)
    @Email
    protected String email;

    @Column(name = "has_handicap")
    protected Boolean hasHandicap;

    @Column(name = "socio_cultural_group")
    protected String socioCulturalGroup;

    @Column(name = "handicap_type")
    protected String handicapType;

    @Column(name = "other_handicap_type")
    protected String otherHandicapType;
    @Column(name = "has_id_doc")
    protected Boolean hasIDDoc;

    @OneToOne(cascade = CascadeType.ALL)
    protected IdentificationDoc identificationDoc;

    @Column(name = "witness_uin")
    //@ExistsInDB(entity = Actor.class, field = "uin", message = "le NIU du témoin n'existe pas !")
    protected String witnessUIN;

    //@NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status")
    protected RegistrationStatus registrationStatus;

    @Column(name = "status_observation")
    protected String statusObservation;

    @Column(name = "rid", unique = true)
    protected String rid;

    @Column(name = "synchro_batch_number")
    protected String synchroBatchNumber;

    @Column(name = "synchro_packet_number", unique = true)
    protected String synchroPacketNumber;

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
