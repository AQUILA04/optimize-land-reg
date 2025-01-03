package com.optimize.land.model.entity;

import com.optimize.common.entities.annotations.ConditionalNotNull;
import com.optimize.common.entities.entity.Auditable;
import com.optimize.land.model.enumeration.RoleActor;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ConditionalNotNull(
        booleanField = "hasConflict",
        dependentField = "conflict",
        message = "Le valeur du conflit est obligatoire lorsque 'hasConflict' est égale à 'true' !"
)
public class Finding extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nup;
    private String region;
    private String prefecture;
    private String commune;
    private String canton;
    private String locality;
    private RoleActor personType;
    private String uin;
    private Boolean hasConflict;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private CheckListOperation firstCheckListOperation;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private CheckListOperation lastCheckListOperation;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Conflict conflict;

}
