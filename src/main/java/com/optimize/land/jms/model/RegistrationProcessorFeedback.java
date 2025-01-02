package com.optimize.land.jms.model;

import lombok.Data;

@Data
public class RegistrationProcessorFeedback {
    private String rid;
    private Boolean isFoundMatch;
    private String matchedRID;

    public RegistrationProcessorFeedback() {
    }

    public RegistrationProcessorFeedback(String rid, Boolean isFoundMatch, String matchedRID) {
        this.rid = rid;
        this.isFoundMatch = isFoundMatch;
        this.matchedRID = matchedRID;
    }

}
