package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.ActorType;
import lombok.Data;

@Data
public class ActorModel {
    private String uin;
    private String name;
    private String firstname;
    private String lastname;
    private ActorType type;
}
