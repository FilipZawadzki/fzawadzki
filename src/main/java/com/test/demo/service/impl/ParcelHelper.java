package com.test.demo.service.impl;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelStatus;
import com.test.demo.mapper.ParcelMapper;
import com.test.demo.repository.DepositBoxRepository;
import com.test.demo.repository.ParcelLockerRepository;
import com.test.demo.repository.ParcelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
class ParcelHelper {


    private final DepositBoxRepository depositBoxRepository;

    private final ParcelLockerRepository parcelLockerRepository;

    private final ParcelRepository parcelRepository;

    boolean addedParcelToGivenParcelLocker(ParcelEntity parcel, Long parcelLockerId) {
        var freeDepositBox = findFreeDepositBoxInParcelLocker(parcelLockerId);
        var freeMultiDepositBox = getMultiDepositBoxWhichCanFitParcel(parcelLockerId, parcel.getReceiver().getId(), parcel.getParcelSize());

        if (freeMultiDepositBox.isPresent()) {
            putParcelToDepositBox(parcel, freeMultiDepositBox.get());
            updateAllParcelStatusInParcelLockerToNow(freeMultiDepositBox.get());
            return true;
        }
        if (freeDepositBox.isPresent()) {
            putParcelToDepositBox(parcel, freeDepositBox.get());
            return true;
        }
        return false;
    }

    private void updateAllParcelStatusInParcelLockerToNow(DepositBoxEntity depositBox) {
        var nowDate = LocalDateTime.now();
        depositBox.getParcelEntities().forEach(parcel -> parcel.setInParcelLockerDate(nowDate));
        parcelRepository.saveAll(depositBox.getParcelEntities());
    }

    private Optional<DepositBoxEntity> getMultiDepositBoxWhichCanFitParcel(Long parcelLockerId, Long clientId, Long size) {
        return depositBoxRepository.findClientDepositBoxes(parcelLockerId, clientId).stream().filter(dp -> dp.getParcelEntities().stream().map(ParcelEntity::getParcelSize).mapToLong(e -> e).sum() + size <= 4).findFirst();
    }

    private Optional<DepositBoxEntity> findFreeDepositBoxInParcelLocker(Long parcelLockerId) {
        var parcelLocker = parcelLockerRepository.findFetchParcelLockerById(parcelLockerId);
        return parcelLocker.flatMap(parcelLockerEntity -> parcelLockerEntity.getDepositBoxes().stream().filter(dp -> dp != null && dp.getParcelEntities().size() == 0).findFirst());
    }

    private void putParcelToDepositBox(ParcelEntity parcel, DepositBoxEntity depositBox) {
        parcel.setDepositBox(depositBox);
        parcel.setParcelLockerActual(depositBox.getParcelLocker());
        parcel.setInParcelLockerDate(LocalDateTime.now());
        parcel.setStatus(ParcelStatus.IN_PARCEL_LOCKER);
        parcelRepository.save(parcel);
    }

    ParcelDto processParcelToFirstFreeOrMultiBoxParcelLocker(ParcelEntity parcel, List<Long> parcelLockerList) {

        var result = parcelLockerList.stream().filter(id -> parcelLockerRepository.findFetchParcelLockerById(id).isPresent() && addedParcelToGivenParcelLocker(parcel, id)).findFirst();

        if (result.isEmpty()) {
            return null;
        }
        return ParcelMapper.toDto(parcel);
    }

}
