package com.test.demo.mapper;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ParcelEntity;

public class ParcelMapper {

    public static ParcelDto toDto(ParcelEntity parcelEntity) {
        ParcelDto parcelDto = new ParcelDto();
        parcelDto.setId(parcelEntity.getId());
        parcelDto.setParcelSize(parcelEntity.getParcelSize());
        parcelDto.setParcelLockerWantedId(parcelEntity.getParcelLockerWanted().getId());
        if (parcelEntity.getParcelLockerActual() != null) {
            parcelDto.setParcelLockerActualId(parcelEntity.getParcelLockerActual().getId());
        }
        parcelDto.setReceiverId(parcelEntity.getReceiver().getId());
        parcelDto.setSenderId(parcelEntity.getSender().getId());
        return parcelDto;
    }
}
