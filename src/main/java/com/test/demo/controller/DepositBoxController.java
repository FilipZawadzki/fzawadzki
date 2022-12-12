package com.test.demo.controller;

import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.repository.DepositBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/depositbox")
class DepositBoxController {

    private final DepositBoxRepository depositBoxRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepositBoxEntity createDepositBox(@RequestBody DepositBoxEntity depositBox) {
        return depositBoxRepository.save(depositBox);
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
