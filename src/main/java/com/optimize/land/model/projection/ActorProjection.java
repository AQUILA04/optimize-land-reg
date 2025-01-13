package com.optimize.land.model.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.RoleActor;

public interface ActorProjection {
    @JsonProperty(value = "id")
    Long getId();
    @JsonProperty(value = "uin")
    String getUin();
    @JsonProperty(value = "registrationStatus")
    RegistrationStatus getRegistrationStatus();
    @JsonProperty(value = "rid")
    String getRid();
    @JsonProperty(value = "role")
    RoleActor getRole();

}
