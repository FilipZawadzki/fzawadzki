package com.test.demo.dto;

import com.test.demo.entity.ParcelStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParcelDto {

    private Long id;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotNull
    private Long parcelLockerWantedId;

    private Long parcelLockerActualId;

    private Long depositBoxId;

    @NotNull
    private Long parcelSize;

    private ParcelStatus parcelStatus;


}
