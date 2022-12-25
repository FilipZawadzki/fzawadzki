package com.test.demo;

import com.test.demo.entity.ClientEntity;
import com.test.demo.entity.DepositBoxEntity;
import com.test.demo.entity.ParcelEntity;
import com.test.demo.entity.ParcelLockerEntity;
import com.test.demo.repository.ClientRepository;
import com.test.demo.repository.DepositBoxRepository;
import com.test.demo.repository.ParcelLockerRepository;
import com.test.demo.repository.ParcelRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestBase {

    @Autowired
    private ParcelLockerRepository parcelLockerRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private DepositBoxRepository depositBoxRepository;

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void cleanUpDb() {
        parcelRepository.deleteAll();
        depositBoxRepository.deleteAll();
        clientRepository.deleteAll();
        parcelLockerRepository.deleteAll();
    }

    public ParcelEntity createParcel(ParcelEntity parcelEntity) {
        return parcelRepository.save(parcelEntity);
    }

    public ClientEntity createClient(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public DepositBoxEntity createDepositBox(DepositBoxEntity depositBox) {
        return depositBoxRepository.save(depositBox);
    }

    public ParcelLockerEntity createParcelLocker(ParcelLockerEntity parcelLockerEntity) {
        return parcelLockerRepository.save(parcelLockerEntity);
    }

}
