package com.test.demo.service.impl;

import com.test.demo.dto.ParcelDto;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelStatus;
import com.test.demo.mapper.ParcelMapper;
import com.test.demo.repository.ClientRepository;
import com.test.demo.repository.ParcelLockerRepository;
import com.test.demo.repository.ParcelRepository;
import com.test.demo.service.ParcelLockerService;
import com.test.demo.service.ParcelService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
class ParcelServiceImpl implements ParcelService {

    private final ClientRepository clientRepository;

    private final ParcelLockerRepository parcelLockerRepository;

    private final ParcelLockerService parcelLockerService;

    private final ParcelRepository parcelRepository;

    private final ParcelHelper parcelHelper;

    @Override
    public ParcelDto postParcel(ParcelDto parcelDto) {
        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setReceiver(clientRepository.getReferenceById(parcelDto.getReceiverId()));
        parcelEntity.setSender(clientRepository.getReferenceById(parcelDto.getSenderId()));
        parcelEntity.setParcelSize(parcelDto.getParcelSize());
        parcelEntity.setParcelLockerWanted(parcelLockerRepository.getReferenceById(parcelDto.getParcelLockerWantedId()));
        parcelEntity.setStatus(ParcelStatus.SENT);
        parcelEntity.setSentDate(LocalDateTime.now());
        parcelEntity = parcelRepository.save(parcelEntity);
        return ParcelMapper.toDto(parcelEntity);
    }

    @Override
    public ParcelDto deliverParcel(Long parcelId) {
        var parcel = parcelRepository.findById(parcelId);
        if (parcel.isEmpty()) {
            return null;
        }
        if (parcelHelper.addedParcelToGivenParcelLocker(parcel.get(), parcel.get().getParcelLockerWanted().getId())) {
            return ParcelMapper.toDto(parcel.get());
        } else if (parcel.get().getParcelLockerWantedSubstitute() != null && parcelHelper.addedParcelToGivenParcelLocker(parcel.get(), parcel.get().getParcelLockerWantedSubstitute().getId())) {
            return ParcelMapper.toDto(parcel.get());
        }

        var parcelLockerList = parcelLockerService.getClosestParcelLockersToGivenParcelLocker(parcel.get().getParcelLockerWanted().getId());

        return parcelHelper.processParcelToFirstFreeOrMultiBoxParcelLocker(parcel.get(), parcelLockerList);
    }

    @Override
    public int changeToReturnAfter48Hours() {
        return parcelRepository.updateAllParcelsWhenStatusInParcelLockersIsLongerThanTwoDays();
    }

    @Override
    public List<ParcelDto> getAllParcels() {
        return parcelRepository.findAll().stream().map(ParcelMapper::toDto).toList();
    }

    @Override
    public ParcelDto getParcelById(Long parcelId) {
        var parcel = parcelRepository.findById(parcelId);
        return parcel.map(ParcelMapper::toDto).orElse(null);
    }

    @Override
    public void deleteParcel(Long parcelId) {
        parcelRepository.deleteById(parcelId);
    }

}
