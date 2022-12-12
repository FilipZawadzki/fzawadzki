package com.test.demo.mapper;

import com.test.demo.dto.ParcelLockerDto;
import com.test.demo.entity.ParcelLockerEntity;

public class ParcelLockerMapper {
    public static ParcelLockerDto toDto(ParcelLockerEntity parcelLockerEntity) {
        if (parcelLockerEntity == null) {
            return null;
        }
        ParcelLockerDto parcelLockerDto = new ParcelLockerDto();
        parcelLockerDto.setAddress(parcelLockerEntity.getAddress());
        parcelLockerDto.setCode(parcelLockerEntity.getCode());
        parcelLockerDto.setAddress(parcelLockerEntity.getAddress());
        parcelLockerDto.setPostCode(parcelLockerEntity.getPostCode());
        parcelLockerDto.setId(parcelLockerEntity.getId());
        parcelLockerDto.setDepositBoxes(parcelLockerDto.getDepositBoxes());
        return parcelLockerDto;
    }
}
