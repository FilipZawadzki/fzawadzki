package com.test.demo.service;

import com.test.demo.dto.ParcelLockerDto;
import com.test.demo.entity.ParcelLockerEntity;

import java.util.List;

public interface ParcelLockerService {

    /**
     * Gets {@link ParcelLockerDto} by ID.
     *
     * @param parcelLockerId - id of the parcel locker to be fetched
     * @return ParcelLockerDto or null if no such entity
     */
    ParcelLockerDto getParcelLockerById(Long parcelLockerId);

    /**
     * Gets closest{@link ParcelLockerEntity} id's to given parcel locker wanted.
     *
     * @param parcelLockerWantedId - id of the wanted parcel locker
     * @return List<Long> of results
     */
    List<Long> getClosestParcelLockersToGivenParcelLocker(Long parcelLockerWantedId);

    /**
     * Creates {@link ParcelLockerEntity} in db from given {@link ParcelLockerDto}.
     *
     * @param parcelLockerDto - parcelLockerDto of a parcel locker to be created
     * @return ParcelLockerDto of created entity
     */
    ParcelLockerDto createParcelLocker(ParcelLockerDto parcelLockerDto);

    /**
     * Deletes {@link ParcelLockerEntity} in DB by ID.
     *
     * @param parcelLockerId - id of the parcel locker to be deleted
     */
    void deleteParcelLocker(Long parcelLockerId);

    /**
     * Gets all {@link ParcelLockerEntity} from DB.
     *
     * @return List<ParcelLockerDto>
     */
    List<ParcelLockerDto> getAllParcelLockers();


}
