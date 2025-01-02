package com.optimize.land.model.mapper;

import com.optimize.common.entities.mapper.BaseMapper;
import com.optimize.land.model.dto.*;
import com.optimize.land.model.entity.*;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ActorMapper extends BaseMapper<AbstractActor, ActorDto> {

    Person toPerson(PersonDto personDto);
    PersonDto toPersonDto(Person person);

    InformalGroup toInformalGroup(InformalGroupDto informalGroupDto);
    InformalGroupDto toInformalGroup(InformalGroup informalGroup);

    PrivateLegalEntity toPrivateLegalEntity(PrivateLegalEntityDto privateLegalEntityDto);
    PrivateLegalEntityDto toPrivateLegalEntityDto(PrivateLegalEntity privateLegalEntity);

    PublicLegalEntity toPublicLegalEntity(PublicLegalEntityDto publicLegalEntityDto);
    PublicLegalEntityDto toPublicLegalEntityDto(PublicLegalEntity publicLegalEntity);

    Registration toRegistration(ActorDto actorDto);
    Actor registrationToActor(Registration registration);
    RegistrationDuplicated registrationToRegistrationDuplicated(Registration registration);
    RegistrationFailed registrationToRegistrationFailed(Registration registration);

    FingerprintStore toFingerprintStore(FingerprintStoreDto fingerprintStoreDto);
    FingerprintStoreDto toFingerprintStoreDto(FingerprintStore fingerprintStore);
    Set<FingerprintStore> toSetFingerprintStore(Set<FingerprintStoreDto> fingerprintStoreDtoSet);
    Set<FingerprintStoreDto> toSetFingerprintStoreDto(Set<FingerprintStore> fingerprintStores);
}
