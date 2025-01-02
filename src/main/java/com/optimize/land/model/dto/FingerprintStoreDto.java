package com.optimize.land.model.dto;

import com.optimize.land.model.enumeration.Finger;
import com.optimize.land.model.enumeration.HandType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FingerprintStoreDto {
    private Long id;
    private String rid;
    @NotNull
    private HandType handType;
    @NotNull
    private Finger fingerName;
    @NotBlank
    private String fingerprintImage;
    private String fingerprintImageContentType;
    private ActorDto actor;
}
