package com.test.demo.repository;

public interface ParcelCustomRepository {

    /**
     * Updates parcels status date 'in parcel locker' to returned where parcels are prolonged (more than 48h in deposit box).
     *
     * @return int number of affected entities
     */
    int updateAllParcelsWhenStatusInParcelLockersIsLongerThanTwoDays();
}
