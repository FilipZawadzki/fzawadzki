package com.test.demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.demo.dto.DepositBoxDto;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.repository.DepositBoxRepository;
import com.test.demo.repository.ParcelLockerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/depositbox")
class DepositBoxController {

    private final DepositBoxRepository depositBoxRepository;
    private final ParcelLockerRepository parcelLockerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepositBoxDto createDepositBox(@RequestBody @Valid DepositBoxDto depositBoxDto) {
        DepositBoxEntity depositBox = new DepositBoxEntity();
        depositBox.setParcelLocker(parcelLockerRepository.getReferenceById(depositBoxDto.getParcelLockerId()));
        depositBox = depositBoxRepository.save(depositBox);
        depositBoxDto.setId(depositBox.getId());
        return depositBoxDto;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepositBox(@RequestBody Long depositBoxId) {
        depositBoxRepository.deleteById(depositBoxId);
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepositBoxEntity getDepositBox(@RequestBody @PathVariable("id") Long depositBoxId) {
        return depositBoxRepository.findById(depositBoxId).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepositBoxEntity> getAllDepositBoxes() {
        return depositBoxRepository.findAll();
    }

}
