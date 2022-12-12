package com.test.demo.controller;

import com.test.demo.entity.ClientEntity;
import com.test.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
class ClientController {

    private final ClientRepository clientRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientEntity createClient(@RequestBody ClientEntity client) {
        return clientRepository.save(client);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientEntity updateClient(@RequestBody ClientEntity client) {
        if (clientRepository.findById(client.getId()).isPresent()) {
            return clientRepository.save(client);
        }
        return null;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@RequestBody Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientEntity getClient(@RequestBody @PathVariable("id") Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }


}
