package com.test.demo.service.impl;

import com.test.demo.IntegrationTestBase;
import com.test.demo.entity.ParcelLockerEntity;
import com.test.demo.service.ParcelLockerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.test.demo.SampleTestData.parcelLocker;
import static org.assertj.core.api.Assertions.assertThat;

class ParcelLockerServiceImplTest extends IntegrationTestBase {

    @Autowired
    private ParcelLockerService parcelLockerService;

    @Test
    void shouldGetAllTwoParcelLockersFromTheSameCity_ToGivenParcelLocker() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-234"));
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        ParcelLockerEntity pl3 = createParcelLocker(parcelLocker("54-236"));
        //when
        List<Long> results = parcelLockerService.getClosestParcelLockersToGivenParcelLocker(pl1.getId());
        //then
        assertThat(results)
                .hasSize(2)
                .extracting(Long::valueOf)
                .containsExactlyInAnyOrder(pl2.getId(), pl3.getId());
    }

    @Test
    void shouldGetOneParcelLockerFromTheSameCity_ToGivenParcelLocker() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-234"));
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        createParcelLocker(parcelLocker("53-236"));
        //when
        List<Long> results = parcelLockerService.getClosestParcelLockersToGivenParcelLocker(pl1.getId());
        //then
        assertThat(results)
                .hasSize(1)
                .extracting(Long::valueOf)
                .containsExactlyInAnyOrder(pl2.getId());
    }

    @Test
    void shouldGetEmptyList_WhenGettingClosestParcelLockersAndThereAreNone() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-234"));
        createParcelLocker(parcelLocker("53-235"));
        createParcelLocker(parcelLocker("53-236"));
        //when
        List<Long> results = parcelLockerService.getClosestParcelLockersToGivenParcelLocker(pl1.getId());
        //then
        assertThat(results).isEmpty();
    }
}