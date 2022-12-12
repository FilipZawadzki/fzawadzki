package com.test.demo.controller;

import com.test.demo.dto.ParcelLockerDto;
import com.test.demo.service.ParcelLockerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/parcellocker")
class ParcelLockerController {

    private final ParcelLockerService parcelLockerService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParcelLockerDto getParcelLockerById(@PathVariable("id") Long parcelLockerId) {
        return parcelLockerService.getParcelLockerById(parcelLockerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParcelLockerDto> getParcelLockerById() {
        return parcelLockerService.getAllParcelLockers();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelLockerDto createParcelLocker(@RequestBody @Valid ParcelLockerDto parcelLockerDto) {
        return parcelLockerService.createParcelLocker(parcelLockerDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParcelLocker(@RequestBody Long parcelLockerId) {
        parcelLockerService.deleteParcelLocker(parcelLockerId);
    }
}
