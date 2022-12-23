package com.test.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositBoxDto {
    private Long id;
    @NotNull
    private Long parcelLockerId;
}
