package com.test.demo;

import com.test.demo.entity.ClientEntity;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelLockerEntity;

import java.util.UUID;

public class SampleTestData {

    public static ParcelEntity parcel(ClientEntity receiver, ClientEntity sender, ParcelLockerEntity parcelLockerWanted, Long parcelSize) {
        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setParcelSize(parcelSize);
        parcelEntity.setParcelLockerWanted(parcelLockerWanted);
        parcelEntity.setSender(sender);
        parcelEntity.setReceiver(receiver);
        return parcelEntity;
    }

    public static DepositBoxEntity depositBox(ParcelLockerEntity parcelLockerEntity) {
        DepositBoxEntity depositBox = new DepositBoxEntity();
        depositBox.setParcelLocker(parcelLockerEntity);
        return depositBox;
    }

    public static ParcelLockerEntity parcelLocker() {
        ParcelLockerEntity parcelLockerEntity = new ParcelLockerEntity();
        parcelLockerEntity.setAddress(UUID.randomUUID().toString());
        parcelLockerEntity.setCode(UUID.randomUUID().toString());
        parcelLockerEntity.setPostCode(UUID.randomUUID().toString());
        return parcelLockerEntity;
    }

    public static ParcelLockerEntity parcelLocker(String postCode) {
        ParcelLockerEntity parcelLockerEntity = new ParcelLockerEntity();
        parcelLockerEntity.setAddress(UUID.randomUUID().toString());
        parcelLockerEntity.setCode(UUID.randomUUID().toString());
        parcelLockerEntity.setPostCode(postCode);
        return parcelLockerEntity;
    }

    public static ClientEntity client() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(UUID.randomUUID().toString());
        clientEntity.setLastName(UUID.randomUUID().toString());
        clientEntity.setPhoneNumber(UUID.randomUUID().toString());
        clientEntity.setEmailAddress(UUID.randomUUID().toString());
        return clientEntity;
    }


}
