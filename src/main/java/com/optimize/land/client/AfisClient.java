package com.optimize.land.client;

import com.optimize.land.model.dto.BioAuthDto;
import com.optimize.land.model.enumeration.BioAuthResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "AFIS-MASTER", url = "http://localhost:8082")
public interface AfisClient {

    @PostMapping(value = "bio-auth")
    BioAuthResponse bioAuthRequest(@RequestBody BioAuthDto dto);

}
