package com.test.demo.dto;

import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.utils.validators.PostCodeConstraint;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ParcelLockerDto {

    @Nullable
    private Long id;

    @PostCodeConstraint
    private String postCode;

    @NotNull
    private String code;

    @NotNull
    private String address;

    @Nullable
    private List<DepositBoxEntity> depositBoxes;
}
