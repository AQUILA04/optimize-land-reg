package com.optimize.land.jms.model;



import com.optimize.land.model.dto.FingerprintStoreDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AfisMasterRequest {
    private String rid;
    private Set<FingerprintStoreDto> fingerprintStores;

    public AfisMasterRequest() {
    }

    public AfisMasterRequest(String rid, Set<FingerprintStoreDto> fingerprintStores) {
        this.rid = rid;
        this.fingerprintStores = fingerprintStores;
    }



}
