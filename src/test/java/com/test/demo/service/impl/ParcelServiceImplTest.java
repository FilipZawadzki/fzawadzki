package com.test.demo.service.impl;

import com.test.demo.IntegrationTestBase;
import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ClientEntity;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelLockerEntity;
import com.test.demo.repository.DepositBoxRepository;
import com.test.demo.repository.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.test.demo.SampleTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParcelServiceImplTest extends IntegrationTestBase {

    @Autowired
    private ParcelServiceImpl parcelService;

    @Autowired
    private ParcelRepository parcelRepository;

    @Test
    void shouldDeliverParcelToParcelLockerWantedWithFreeDeposit_HavingOnlyOneDeposit() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-234"));
        createParcelLocker(parcelLocker("54-235"));
        createParcelLocker(parcelLocker("54-236"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        createDepositBox(depositBox(pl1));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl1, 1L));
        //when
        var result = parcelService.deliverParcel(parcel1.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl1.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerSubstitute_WhenParcelLockerWantedIsFull(){
        //given
        ParcelLockerEntity plWanted = createParcelLocker(parcelLocker("54-234"));
        ParcelLockerEntity plSubstitute = createParcelLocker(parcelLocker("54-235"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        DepositBoxEntity depositBox = createDepositBox(depositBox(plWanted));
        createDepositBox(depositBox(plSubstitute));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, plWanted, 4L));
        parcel1.setDepositBox(depositBox);
        parcelRepository.save(parcel1);
        ParcelEntity parcel2 = createParcel(parcel(receiver1, sender1, plWanted, 4L));
        parcel2.setParcelLockerWantedSubstitute(plSubstitute);
        parcelRepository.save(parcel2);

        //when
        var result = parcelService.deliverParcel(parcel2.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(plSubstitute.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerWantedWithFreeDeposit_HavingOnlyOneDepositFree() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-234"));
        createParcelLocker(parcelLocker("54-235"));
        createParcelLocker(parcelLocker("54-236"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        createDepositBox(depositBox(pl1));
        DepositBoxEntity depositBox = createDepositBox(depositBox(pl1));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl1, 1L));
        ParcelEntity parcel2 = parcel(receiver1, sender1, pl1, 4L);
        parcel2.setDepositBox(depositBox);
        parcelRepository.save(parcel2);
        //when
        var result = parcelService.deliverParcel(parcel1.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl1.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerWantedWithFreeDeposit_Having3DifferentParcelLockersWithTwoDepositFree() {
        //given
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        createParcelLocker(parcelLocker("54-236"));
        ParcelLockerEntity pl3 = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        createDepositBox(depositBox(pl3));
        DepositBoxEntity depositBox = createDepositBox(depositBox(pl3));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl3, 1L));
        ParcelEntity parcel2 = parcel(receiver1, sender1, pl2, 4L);
        parcel2.setDepositBox(depositBox);
        parcelRepository.save(parcel2);
        //when
        var result = parcelService.deliverParcel(parcel1.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl3.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerClosestWithFreeDeposit_HavingParcelLockerWantedFull() {
        //given
        createParcelLocker(parcelLocker("54-236"));
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        ParcelLockerEntity pl3 = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        createDepositBox(depositBox(pl2));
        DepositBoxEntity depositBox = createDepositBox(depositBox(pl3));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl3, 3L));
        ParcelEntity parcel2 = parcel(receiver1, sender1, pl3, 4L);
        parcel2.setDepositBox(depositBox);
        parcelRepository.save(parcel2);
        //when
        var result = parcelService.deliverParcel(parcel1.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl2.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerClosestWithFreeDeposit_HavingParcelLockerWantedFullAndFirstClosestParcelLockerFull() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-236"));
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        ParcelLockerEntity pl3 = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        DepositBoxEntity depositBox1 = createDepositBox(depositBox(pl1));
        DepositBoxEntity depositBox2 = createDepositBox(depositBox(pl2));
        createDepositBox(depositBox(pl3));
        ParcelEntity parcel1 = parcel(receiver1, sender1, pl1, 4L);
        ParcelEntity parcel2 = parcel(receiver1, sender1, pl1, 4L);
        ParcelEntity parcel3 = createParcel(parcel(receiver1, sender1, pl1, 4L));
        parcel1.setDepositBox(depositBox1);
        parcel2.setDepositBox(depositBox2);
        parcelRepository.save(parcel1);
        parcelRepository.save(parcel2);
        //when
        var result = parcelService.deliverParcel(parcel3.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl3.getId());
    }

    @Test
    void shouldDeliverParcelToParcelLockerClosestWithFreeMultiDeposit_HavingParcelLockerWantedFull() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-236"));
        ParcelLockerEntity pl2 = createParcelLocker(parcelLocker("54-235"));
        ParcelLockerEntity pl3 = createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        DepositBoxEntity depositBox1 = createDepositBox(depositBox(pl1));
        DepositBoxEntity depositBox2 = createDepositBox(depositBox(pl2));
        createDepositBox(depositBox(pl3));
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl1, 1L));
        ParcelEntity parcel2 = parcel(receiver1, sender1, pl1, 3L);
        ParcelEntity parcel3 = parcel(receiver1, sender1, pl1, 4L);
        parcel3.setDepositBox(depositBox1);
        parcel2.setDepositBox(depositBox2);
        parcelRepository.save(parcel2);
        parcelRepository.save(parcel3);
        //when
        var result = parcelService.deliverParcel(parcel1.getId());
        //then
        assertThat(result)
                .isNotNull()
                .extracting(ParcelDto::getParcelLockerActualId)
                .isEqualTo(pl2.getId());
    }

    @Test
    void shouldChangeStatusOfTwoParcelsToReturnWhenLongerInParcelLockerThan48Hours() {
        //given
        ParcelLockerEntity pl1 = createParcelLocker(parcelLocker("54-236"));
        createParcelLocker(parcelLocker("54-235"));
        createParcelLocker(parcelLocker("54-234"));
        ClientEntity receiver1 = createClient(client());
        ClientEntity sender1 = createClient(client());
        ParcelEntity parcel1 = createParcel(parcel(receiver1, sender1, pl1, 1L));
        ParcelEntity parcel2 = createParcel(parcel(receiver1, sender1, pl1, 3L));
        ParcelEntity parcel3 = createParcel(parcel(receiver1, sender1, pl1, 3L));
        parcel1.setInParcelLockerDate(LocalDateTime.parse("2018-12-30T19:34:50.63"));
        parcel2.setInParcelLockerDate(LocalDateTime.parse("2018-12-30T19:34:50.63"));
        parcel3.setInParcelLockerDate(LocalDateTime.now());
        parcelRepository.save(parcel1);
        parcelRepository.save(parcel2);
        parcelRepository.save(parcel3);
        //when
        var result = parcelService.changeToReturnAfter48Hours();
        //then
        assertThat(result)
                .isEqualTo(2);
    }


}