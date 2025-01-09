package com.optimize.land.model.projection;

import com.optimize.land.model.enumeration.RoleActor;

public interface FindingProjection {
     Long getId();
     String getNup();
     String getRegion();
     String getPrefecture();
     String getCommune();
     String getCanton();
     String getLocality();
     RoleActor getPersonType();
     String getUin();
     Boolean getHasConflict();
}
