package com.test.demo.controller;

import com.test.demo.dto.ParcelDto;
import com.test.demo.service.ParcelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/parcel")
class ParcelController {

    private final ParcelService parcelService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParcelDto getParcelById(@PathVariable("id") Long parcelId) {
        return parcelService.getParcelById(parcelId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParcelDto> getParcelById() {
        return parcelService.getAllParcels();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelDto createParcel(@RequestBody @Valid ParcelDto parcelDto) {
        return parcelService.postParcel(parcelDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParcel(@RequestBody Long parcelId) {
        parcelService.deleteParcel(parcelId);
    }

    @PutMapping("/deliver/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParcelDto deliverParcel(@PathVariable("id") Long parcelId) {
        return parcelService.deliverParcel(parcelId);
    }

}
