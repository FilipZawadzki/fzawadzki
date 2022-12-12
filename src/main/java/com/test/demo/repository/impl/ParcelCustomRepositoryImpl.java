package com.test.demo.repository.impl;

import com.test.demo.entity.ParcelStatus;
import com.test.demo.repository.ParcelCustomRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@AllArgsConstructor
class ParcelCustomRepositoryImpl implements ParcelCustomRepository {

    public static final String UPDATE_ALL_PARCELS_WHEN_STATUS_IN_PARCEL_LOCKER_LONGER_THAN_TWO_DAYS =
            "update ParcelEntity p set p.inParcelLockerDate = :date, p.status = :status, p.updateDate = :date " +
                    "where p.inParcelLockerDate<= :dateOfReturn and p.receivedDate IS NULL";
    @PersistenceContext
    private final EntityManager em;

    @Override
    public int updateAllParcelsWhenStatusInParcelLockersIsLongerThanTwoDays() {
        var dateNow = LocalDateTime.now();
        return em.createQuery(UPDATE_ALL_PARCELS_WHEN_STATUS_IN_PARCEL_LOCKER_LONGER_THAN_TWO_DAYS)
                .setParameter("date", dateNow)
                .setParameter("dateOfReturn", dateNow.minusHours(48))
                .setParameter("status", ParcelStatus.RETURNED)
                .executeUpdate();
    }
}
