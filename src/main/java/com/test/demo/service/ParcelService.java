package com.test.demo.service;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.mail.Mail;

import java.util.List;

public interface ParcelService {

    /**
     * Posts(creates) {@link ParcelEntity} in db from given {@link ParcelDto} and sets sent status.
     *
     * @param parcelDto - parcelDto of a parcel to be sent
     * @return ParcelDto of created entity
     */
    ParcelDto postParcel(ParcelDto parcelDto);

    /**
     * Delivers {@link ParcelEntity}. Delivered parcel updates it's status and status date,
     * sets parcel locker actual and deposit box. If it is delivered to multi box
     * it updates all parcels in deposit box 'in parcel locker' status dates to new
     *
     * @param parcelId - parcel id of a parcel to be delivered
     * @return ParcelDto of delivered entity
     */
    ParcelDto deliverParcel(Long parcelId);

    /**
     * Gets all {@link ParcelDto} from db.
     *
     * @return List <ParcelDto> of found entities
     */
    List<ParcelDto> getAllParcels();

    /**
     * Gets {@link ParcelDto} from DB
     *
     * @param parcelId - parcel id to be found
     * @return ParcelDto of found entity
     */
    ParcelDto getParcelById(Long parcelId);

    /**
     * Deletes {@link ParcelEntity} in DB by id.
     *
     * @param parcelId - id of a parcel to be deleted
     */
    void deleteParcel(Long parcelId);

    /**
     * Changes statuses of {@link ParcelEntity} in DB to return when they are prolonged in parcel locker
     * for longer than 48h.
     *
     */
    int changeToReturnAfter48Hours();

}
