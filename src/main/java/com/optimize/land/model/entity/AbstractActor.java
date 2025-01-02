package com.optimize.land.model.entity;

import com.optimize.common.entities.entity.Auditable;
import com.optimize.common.entities.exception.CustomValidationException;
import com.optimize.land.model.enumeration.ActorType;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.RoleActor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractActor extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "actorSequenceGenerator")
    protected Long id;

    @OneToOne
    protected Person physicalPerson;
    @OneToOne
    protected InformalGroup informalGroup;
    @OneToOne
    protected PrivateLegalEntity privateLegalEntity;
    @OneToOne
    protected PublicLegalEntity publicLegalEntity;

    @Size(min = 10, max = 15)
    @Column(name = "uin", length = 15, unique = true)
    protected String uin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status", nullable = false)
    protected RegistrationStatus registrationStatus;

    @Column(name = "status_observation")
    protected String statusObservation;

    @Column(name = "rid")
    protected String rid;

    @Column(name = "synchro_batch_number")
    protected String synchroBatchNumber;

    @Column(name = "synchro_packet_number")
    protected String synchroPacketNumber;

    @Enumerated(EnumType.STRING)
    protected RoleActor role;

    @Enumerated(EnumType.STRING)
    protected ActorType type;
    @OneToMany
    protected Set<FingerprintStore> fingerprintStores;

    private void validateUniqueActorType () {
            int nonNullCount = 0;

            if (physicalPerson != null) nonNullCount++;
            if (informalGroup != null) nonNullCount++;
            if (privateLegalEntity != null) nonNullCount++;
            if (publicLegalEntity != null) nonNullCount++;

            if (nonNullCount > 1) {
                throw new CustomValidationException("Only one attribute can be non-null. Found multiple non-null attributes in ['physicalPerson', 'informalGroup', 'privateLegalEntity', 'publicLegalEntity'].");
            }

        if (physicalPerson != null) {
            type = ActorType.PHYSICAL_PERSON;
        } else if (informalGroup != null) {
            type = ActorType.INFORMAL_GROUP;
        } else if (privateLegalEntity != null) {
            type = ActorType.PRIVATE_LEGAL_ENTITY;
        } else if (publicLegalEntity != null) {
            type = ActorType.PUBLIC_LEGAL_ENTITY;
        }
    }

    public void updateFingerprint() {
        fingerprintStores.forEach(fs -> fs.setActor(this));
    }

    public void addRid(String rid) {
        this.rid = rid;
        this.registrationStatus = RegistrationStatus.PENDING;
        fingerprintStores.forEach(fs -> fs.setRid(rid));
    }
}
