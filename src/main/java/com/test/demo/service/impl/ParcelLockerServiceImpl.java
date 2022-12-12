package com.test.demo.service.impl;

import com.test.demo.dto.ParcelLockerDto;
import com.test.demo.entity.ParcelLockerEntity;
import com.test.demo.mapper.ParcelLockerMapper;
import com.test.demo.repository.ParcelLockerRepository;
import com.test.demo.service.ParcelLockerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
class ParcelLockerServiceImpl implements ParcelLockerService {

    private final ParcelLockerRepository parcelLockerRepository;

    @Override
    public ParcelLockerDto getParcelLockerById(Long parcelLockerId) {
        return ParcelLockerMapper.toDto(parcelLockerRepository.findById(parcelLockerId).orElse(null));
    }

    @Override
    public List<Long> getClosestParcelLockersToGivenParcelLocker(Long parcelLockerWantedId) {
        String postCode = parcelLockerRepository.findById(parcelLockerWantedId).get().getPostCode();
        List<ParcelLockerEntity> sameCityParcelLockers = parcelLockerRepository.findParcelLockersFromTheSameCity(postCode.substring(0, 2));

        return sameCityParcelLockers.stream()
                .filter(pl -> pl.getId() != parcelLockerWantedId)
                .sorted(Comparator.comparingInt(s1 -> Math.abs(Integer.parseInt(s1.getPostCode().substring(3, 6)) - Integer.parseInt(postCode.substring(3, 6)))))
                .map(ParcelLockerEntity::getId).toList();
    }

    @Override
    public ParcelLockerDto createParcelLocker(@Valid ParcelLockerDto parcelLockerDto) {
        ParcelLockerEntity parcelLockerEntity = new ParcelLockerEntity();
        parcelLockerEntity.setPostCode(parcelLockerDto.getPostCode());
        parcelLockerEntity.setAddress(parcelLockerDto.getAddress());
        parcelLockerEntity.setCode(parcelLockerDto.getCode());
        return ParcelLockerMapper.toDto(parcelLockerRepository.save(parcelLockerEntity));
    }

    @Override
    public void deleteParcelLocker(Long parcelLockerId) {
        parcelLockerRepository.deleteById(parcelLockerId);
    }

    @Override
    public List<ParcelLockerDto> getAllParcelLockers() {
        return parcelLockerRepository.findAll().stream().map(ParcelLockerMapper::toDto).toList();
    }


}
