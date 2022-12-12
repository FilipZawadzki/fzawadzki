package com.test.demo.repository;

import com.test.demo.entity.ClientEntity;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.entity.ParcelLockerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositBoxRepository extends JpaRepository<DepositBoxEntity, Long> {

    String FIND_MULTI_DEPOSITS_IN_GIVEN_PARCEL_LOCKER_WITH_GIVEN_CLIENT_PARCELS =
            "select d from DepositBoxEntity d JOIN d.parcelLocker pl JOIN d.parcelEntities p " +
                    "where pl.id = :parcelLockerId and p.receiver.id = :clientId";


    /**
     * Finds all {@link DepositBoxEntity} with given {@link ClientEntity} parcels in given {@link ParcelLockerEntity}.
     *
     * @return List<DepositBoxEntity> of results
     */
    @Query(FIND_MULTI_DEPOSITS_IN_GIVEN_PARCEL_LOCKER_WITH_GIVEN_CLIENT_PARCELS)
    List<DepositBoxEntity> findClientDepositBoxes(
            @Param("parcelLockerId") Long parcelLockerId, @Param("clientId") Long clientId);
}
