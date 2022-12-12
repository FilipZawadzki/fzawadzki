package com.test.demo.repository;

import com.test.demo.entity.ParcelLockerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelLockerRepository extends JpaRepository<ParcelLockerEntity, Long> {

    String FIND_PARCEL_LOCKERS_BY_THE_SAME_CITY_POST_CODE = "select p from ParcelLockerEntity p where p.postCode like :postCode%";

    String FIND_FETCH_PARCEL_LOCKER_BY_ID = "select pl from ParcelLockerEntity pl JOIN FETCH pl.depositBoxes WHERE pl.id = :id";

    /**
     * Finds all {@link ParcelLockerEntity} matching given first two numbers of the city's parcel locker post code.
     *
     * @return List<ParcelLockerEntity> of results
     */
    @Query(FIND_PARCEL_LOCKERS_BY_THE_SAME_CITY_POST_CODE)
    List<ParcelLockerEntity> findParcelLockersFromTheSameCity(@Param("postCode") String firstTwoNumbersOfPostCode);

    /**
     * Finds {@link ParcelLockerEntity} with fetched deposit boxes.
     *
     * @return Optional<ParcelLockerEntity> of result
     */
    @Query(FIND_FETCH_PARCEL_LOCKER_BY_ID)
    Optional<ParcelLockerEntity> findFetchParcelLockerById(@Param("id") Long id);


}
