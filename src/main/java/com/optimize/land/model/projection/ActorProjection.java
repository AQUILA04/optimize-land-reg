package com.optimize.land.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.model.enumeration.RoleActor;

import java.time.LocalDateTime;

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
    String getStatusObservation();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getCreatedDate();

}
